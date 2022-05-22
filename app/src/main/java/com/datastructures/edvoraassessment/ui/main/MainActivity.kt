package com.datastructures.edvoraassessment.ui.main

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.datastructures.edvoraassessment.R
import com.datastructures.edvoraassessment.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.options_dialog.view.*


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel by lazy {
         ViewModelProvider(this)[ViewModel::class.java]
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.getUser()

        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = binding.viewPager
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = binding.tabs
        tabs.setupWithViewPager(viewPager)
        initUser()
        initOptions()


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
                .create()

            builder.requestWindowFeature(Window.FEATURE_NO_TITLE)
            val layoutParams: WindowManager.LayoutParams = builder.window!!.attributes

            layoutParams.gravity = Gravity.TOP or Gravity.LEFT
            layoutParams.x = 100 //x position

            layoutParams.y = 100 //y position


            builder.show()

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
            viewModel.getRides(it.getStationCode())
            Log.d("USER :::",it.getStationCode().toString())
            user_name.text = it.getName()
            Glide.with(applicationContext).load(it.getUrl()).into(user_image)

        }

    }
}

/*
*


        *
        * */