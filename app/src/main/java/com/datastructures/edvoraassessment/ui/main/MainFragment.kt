package com.datastructures.edvoraassessment.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.datastructures.edvoraassessment.R
import com.datastructures.edvoraassessment.adapters.RideListAdapter
import com.datastructures.edvoraassessment.interfaces.APIInterface
import com.datastructures.edvoraassessment.models.RidesModel
import kotlinx.android.synthetic.main.fragment_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainFragment : Fragment() {
    private var ridesArrayList : ArrayList<RidesModel> = ArrayList()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        buildList()

        val adapter  = RideListAdapter(ridesArrayList)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

    }


    private fun buildList() {

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://assessment.api.vweb.app/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiInterface = retrofit.create(APIInterface::class.java)
        val call: Call<List<RidesModel>> = apiInterface.getRide()

        if(ridesArrayList.isEmpty()){

            call.enqueue(object : Callback<List<RidesModel>> {
                override fun onResponse(
                    call: Call<List<RidesModel>>,
                    response: Response<List<RidesModel>>
                ) {

                    if (response.isSuccessful) {
                        for (i in 0 until response.body()!!.size) {
                            ridesArrayList.add(response.body()!![i])
                            Log.d("Arr :::", ridesArrayList[i].getStation_path()!![0].toString())
                        }

                    } else {
                        meow.text = response.body()!![0].toString()
                        Log.d("API call :::", "2")
                    }

                }

                override fun onFailure(call: Call<List<RidesModel>>, t: Throwable) {
                    meow.text = t.message
                    Log.d("API call :::", "3")

                }
            })
        }
    }

}