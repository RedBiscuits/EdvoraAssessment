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
import kotlinx.android.synthetic.main.fragment_main.*

class PastRidesFragment:Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val ridesViewModel = ViewModelProvider(requireActivity())[ViewModel::class.java]

        ridesViewModel.mutablePastRidesLiveData.observe(viewLifecycleOwner) {

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