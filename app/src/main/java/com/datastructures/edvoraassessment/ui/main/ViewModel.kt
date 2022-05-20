package com.datastructures.edvoraassessment.ui.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.datastructures.edvoraassessment.models.RidesModel
import com.datastructures.edvoraassessment.models.UserModel
import kotlinx.coroutines.launch

class ViewModel : ViewModel() {
     var mutableRidesLiveData : MutableLiveData<RidesModel> = MutableLiveData()
    fun getRides(){
        viewModelScope.launch{
            mutableRidesLiveData.value =ApiResponse.apiInterface.getRide().body()
            Log.d("asddqweqwr " , ApiResponse.apiInterface.getRide().body().toString())
        }
    }
     var mutableUserLiveData: MutableLiveData<UserModel> = MutableLiveData()
    fun getUser(){
        viewModelScope.launch {
            mutableUserLiveData.value =ApiResponse.apiInterface.getUser().body()

        }
    }
}