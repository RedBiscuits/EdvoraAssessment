package com.datastructures.edvoraassessment.models

class UserModel {
    private var station_code: Int = 0
    private var name: String? = null
    private var url: String? = null

    fun getName():String?{
        return name
    }
    fun getUrl():String?{
        return url
    }
    fun getStationCode():Int{
        return station_code
    }

    init {
        this.station_code = 0
        this.name = "edvora"
        this.url = "https://picsum.photos/200"
    }
}