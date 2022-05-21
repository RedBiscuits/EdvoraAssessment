package com.datastructures.edvoraassessment.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.datastructures.edvoraassessment.R
import com.datastructures.edvoraassessment.adapters.RideListAdapter
import com.datastructures.edvoraassessment.models.RidesModel
import kotlinx.android.synthetic.main.fragment_main.*


class MainFragment : Fragment() {
    private lateinit var ridesArrayList : RidesModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("asd" , "asdasd")
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val ridesViewModel = ViewModelProvider(requireActivity())[ViewModel::class.java]
        ridesViewModel.mutableRidesLiveData.observe(viewLifecycleOwner) {
            ridesArrayList = it

            val adapter  = RideListAdapter(it)
            recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
        }
        ridesViewModel.mutableFilteredRidesLiveData.observe(viewLifecycleOwner) {

            val adapter  = RideListAdapter(it)
            recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
        }


    }




}