package com.datastructures.edvoraassessment.ui.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.datastructures.edvoraassessment.models.RidesModel
import com.datastructures.edvoraassessment.models.RidesModelItem
import com.datastructures.edvoraassessment.models.UserModel
import kotlinx.coroutines.launch
import java.lang.Math.abs

class ViewModel : ViewModel() {
     var mutableRidesLiveData : MutableLiveData<RidesModel> = MutableLiveData()
    fun getRides(distance : Int){
        viewModelScope.launch{
            Log.d("hoho" , "arr.toString()")
            var arr =ApiResponse.apiInterface.getRide().body()
            for ( i in arr!!){
                i.distance = kotlin.math.abs(
                    binarySearchClosest(
                        i.station_path,
                        i.station_path.size - 1,
                        distance
                    ) - distance
                )
            }
            Log.d("hoho" , arr.toString())
            arr.sortBy { it.distance }
            mutableRidesLiveData.value = arr!!
        }
    }
     var mutableUserLiveData: MutableLiveData<UserModel> = MutableLiveData()
    fun getUser(){
        viewModelScope.launch {
            mutableUserLiveData.value =ApiResponse.apiInterface.getUser().body()

        }
    }
    var mutableFilteredRidesLiveData : MutableLiveData<ArrayList<RidesModelItem>> = MutableLiveData()
    fun getFilteredRides(filter : String , determinant : String , secondFilter : String? = "") {
        var rides = mutableRidesLiveData.value as ArrayList<RidesModelItem>
        if(filter != ""){
        if (determinant == "state"){
            rides = rides!!.filter {
                it.state == filter
            } as ArrayList<RidesModelItem>
        } else if (determinant == "city"){
            rides = rides!!.filter {
                it.city == filter
            } as ArrayList<RidesModelItem>
        } else if (determinant == "state_city"){
            rides = rides!!.filter {
                it.state == filter && it.city ==secondFilter
            } as ArrayList<RidesModelItem>
        }
        }
        mutableFilteredRidesLiveData.value = rides
    }









    fun binarySearchClosest(arr: ArrayList<Int>, n: Int, target: Int): Int {
        if (target <= arr[0]) return arr[0]
        if (target >= arr[n - 1]) return arr[n - 1]

        var i = 0
        var j = n
        var mid = 0
        while (i < j) {
            mid = (i + j) / 2
            if (arr[mid] == target) return arr[mid]

            if (target < arr[mid]) {


                if (mid > 0 && target > arr[mid - 1]) return getClosest(
                    arr[mid - 1],
                    arr[mid], target
                )

                j = mid
            } else {
                if (mid < n - 1 && target < arr[mid + 1]) return getClosest(
                    arr[mid],
                    arr[mid + 1], target
                )

                i = mid + 1
            }
        }


        return arr[mid]
    }


    fun getClosest(val1: Int, val2: Int, target: Int): Int {
        return if (target - val1 >= val2 - target) val2 else val1
    }


}

/*
* Log.d("rides"  , rides?.size.toString())
        for (ride in rides!!){
            if(determinant == "city"){
                if(ride.city == filter){
                    arr.add(ride)
                }
            }else if (determinant == "state"){
                if(ride.state == filter){
                    arr.add(ride)
                }
            }
        }*/