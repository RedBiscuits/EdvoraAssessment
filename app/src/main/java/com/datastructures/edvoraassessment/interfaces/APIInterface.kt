package com.datastructures.edvoraassessment.interfaces

import com.datastructures.edvoraassessment.models.RidesModel
import com.datastructures.edvoraassessment.models.UserModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface APIInterface {
    @GET("rides")
    public fun getRide() : Call<List<RidesModel>>

    @GET("user")
    public fun getUser() : Call<UserModel>

}