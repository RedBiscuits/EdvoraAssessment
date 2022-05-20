package com.datastructures.edvoraassessment.ui.main

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.datastructures.edvoraassessment.databinding.ActivityMainBinding
import com.datastructures.edvoraassessment.models.UserModel
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*


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
        initUser()


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