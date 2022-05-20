package com.datastructures.edvoraassessment.ui.main

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.datastructures.edvoraassessment.databinding.ActivityMainBinding
import com.datastructures.edvoraassessment.interfaces.APIInterface
import com.datastructures.edvoraassessment.models.UserModel
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var user :UserModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = binding.viewPager
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = binding.tabs
        tabs.setupWithViewPager(viewPager)
        initUser()


    }
    private fun initUser(){
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://assessment.api.vweb.app/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val userApi = retrofit.create(APIInterface::class.java)
        val userCall: Call<UserModel> = userApi.getUser()
        userCall.enqueue(object : Callback<UserModel> {

            override fun onResponse(call: Call<UserModel>, response: Response<UserModel>) {
                user = response.body()!!
                Log.d("USER :::","Success")
                user_name.text = user.getName()
                Glide.with(applicationContext).load(user.getUrl()).into(user_image)

            }

            override fun onFailure(call: Call<UserModel>, t: Throwable) {
                Log.d("USER :::","Failure")

            }
        })
    }
}