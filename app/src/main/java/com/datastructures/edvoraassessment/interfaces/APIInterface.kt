package com.datastructures.edvoraassessment.interfaces

import com.datastructures.edvoraassessment.models.RidesModel
import com.datastructures.edvoraassessment.models.UserModel
import retrofit2.Response
import retrofit2.http.GET

interface APIInterface {
    @GET("rides")
    suspend fun getRide() : Response<RidesModel>

    @GET("user")
    suspend fun getUser() : Response<UserModel>

}