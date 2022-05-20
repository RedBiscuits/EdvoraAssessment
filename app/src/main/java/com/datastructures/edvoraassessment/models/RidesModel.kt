package com.datastructures.edvoraassessment.models

import java.io.Serializable

class RidesModel :Serializable{
    private var id = 0
    private var origin_station_code = 0
    private lateinit var station_path: IntArray
    private var destination_station_code = 0
    private var date: String? = null
    private var map_url: String? = null
    private var state: String? = null
    private var city: String? = null
    private var distance : Int =0

    fun getId(): Int {
        return id
    }

    override fun toString(): String {
        return super.toString()
    }

    fun setId(id: Int) {
        this.id = id
    }
    fun getDistance(): Int {
        return distance
    }

    fun setDistance(distance: Int) {
        this.distance = distance
    }

    fun getOrigin_station_code(): Int {
        return origin_station_code
    }

    fun setOrigin_station_code(origin_station_code: Int) {
        this.origin_station_code = origin_station_code
    }

    fun getStation_path(): IntArray? {
        return station_path
    }

    fun setStation_path(station_path: IntArray) {
        this.station_path = station_path
    }

    fun getDestination_station_code(): Int {
        return destination_station_code
    }

    fun setDestination_station_code(destination_station_code: Int) {
        this.destination_station_code = destination_station_code
    }

    fun getDate(): String? {
        return date
    }

    fun setDate(date: String?) {
        this.date = date
    }

    fun getMap_url(): String? {
        return map_url
    }

    fun setMap_url(map_url: String?) {
        this.map_url = map_url
    }

    fun getState(): String? {
        return state
    }

    fun setState(state: String?) {
        this.state = state
    }

    fun getCity(): String? {
        return city
    }

    fun setCity(city: String?) {
        this.city = city
    }

    fun meow(
        id: Int,
        origin_station_code: Int,
        station_path: IntArray,
        destination_station_code: Int,
        date: String?,
        map_url: String?,
        state: String?,
        city: String?,
        distance: Int
    ) {
        this.id = id
        this.origin_station_code = origin_station_code
        this.station_path = station_path
        this.destination_station_code = destination_station_code
        this.date = date
        this.map_url = map_url
        this.state = state
        this.city = city
        this.distance = distance
    }
}


/*
*
* "id":265,
* "origin_station_code":9,
* "station_path":[33,46,58,64,72,86],
* "destination_station_code":94,
* "date":"03/15/2022 05:26 AM",
* "map_url":"https://picsum.photos/200",
* "state":"Arunachal Pradesh",
* "city":"Naharlagun"*/