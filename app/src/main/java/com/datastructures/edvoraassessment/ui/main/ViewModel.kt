package com.datastructures.edvoraassessment.ui.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.datastructures.edvoraassessment.models.RidesModel
import com.datastructures.edvoraassessment.models.RidesModelItem
import com.datastructures.edvoraassessment.models.UserModel
import kotlinx.coroutines.launch

class ViewModel : ViewModel() {
     var mutableRidesLiveData : MutableLiveData<RidesModel> = MutableLiveData()
    fun getRides(){
        viewModelScope.launch{
            mutableRidesLiveData.value =ApiResponse.apiInterface.getRide().body()
        }
    }
     var mutableUserLiveData: MutableLiveData<UserModel> = MutableLiveData()
    fun getUser(){
        viewModelScope.launch {
            mutableUserLiveData.value =ApiResponse.apiInterface.getUser().body()

        }
    }
    var mutableFilteredRidesLiveData : MutableLiveData<ArrayList<RidesModelItem>> = MutableLiveData()
    fun getFilteredRides(filter : String , determinant : String) {
        val rides = mutableRidesLiveData.value
        var arr : ArrayList<RidesModelItem> = ArrayList()
        Log.d("rides"  , rides?.size.toString())
        for (i in 1 until rides!!.size){
            if(determinant == "city"){
                if(rides[i].city == filter){
                    arr.add(rides[i])
                }
            }else if (determinant == "state"){
                if(rides[i].state == filter){
                    arr.add(rides[i])
                }
            }
        }
        mutableFilteredRidesLiveData.value = arr
    }
}