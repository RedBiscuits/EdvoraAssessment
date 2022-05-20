package com.datastructures.edvoraassessment.models

data class RidesModelItem(
    val city: String,
    val date: String,
    val destination_station_code: Int,
    val id: Int,
    val map_url: String,
    val origin_station_code: Int,
    val state: String,
    val station_path: ArrayList<Int>,
    val distance: Int = 0
)