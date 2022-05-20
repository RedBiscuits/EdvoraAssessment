package com.datastructures.edvoraassessment.ui.main

import com.datastructures.edvoraassessment.interfaces.APIInterface
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiResponse {

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://assessment.api.vweb.app/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiInterface = retrofit.create(APIInterface::class.java)

}