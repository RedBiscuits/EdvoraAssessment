package com.datastructures.edvoraassessment.ui.main

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.datastructures.edvoraassessment.R
import com.datastructures.edvoraassessment.databinding.ActivityMainBinding
import com.datastructures.edvoraassessment.models.UserModel
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.options_dialog.view.*


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var user :UserModel

    private val viewModel by lazy {
         ViewModelProvider(this)[ViewModel::class.java]
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.getRides()
        viewModel.getUser()

        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = binding.viewPager
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = binding.tabs
        tabs.setupWithViewPager(viewPager)
        initOptions()
        initUser()


    }

    @SuppressLint("ResourceAsColor")
    private fun initOptions() {
        val cities = resources.getStringArray(R.array.cities)
        val states = resources.getStringArray(R.array.states)

        val citiesArrayAdapter = ArrayAdapter(this ,R.layout.spinner_list_item , cities)
        val statesArrayAdapter = ArrayAdapter(this ,R.layout.spinner_list_item , states)

        options.setOnClickListener {
            val dialogView = LayoutInflater.from(this).inflate(R.layout.options_dialog,null)
            val builder =AlertDialog.Builder(this)
                .setView(dialogView)


            val alertDialog = builder.show()
            dialogView.cities_filter.setAdapter(citiesArrayAdapter)
            dialogView.states_filter.setAdapter(statesArrayAdapter)

            dialogView.cities_filter.setOnItemClickListener { _: AdapterView<*>, _: View, i: Int, _: Long ->
                viewModel.getFilteredRides(cities[i],"city")
            }
            dialogView.states_filter.setOnItemClickListener { _: AdapterView<*>, _: View, i: Int, _: Long ->
                viewModel.getFilteredRides(states[i],"state")
            }

        }

    }


    private fun initUser(){

        viewModel.mutableUserLiveData.observe(this) {
            user = it
            Log.d("USER :::","Success")
            user_name.text = user.getName()
            Glide.with(applicationContext).load(user.getUrl()).into(user_image)
        }

    }
}

/*
*


        *
        * */