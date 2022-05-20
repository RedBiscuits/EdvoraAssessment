package com.datastructures.edvoraassessment.adapters

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.datastructures.edvoraassessment.R
import com.datastructures.edvoraassessment.models.RidesModel
import com.google.android.material.imageview.ShapeableImageView

class RideListAdapter(private var ridesArray: ArrayList<RidesModel>) : RecyclerView.Adapter<RideListAdapter.RideViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RideViewHolder {
        return RideViewHolder(LayoutInflater
            .from(parent.context)
            .inflate(R.layout.ride_list_item ,
                     parent , false))

    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: RideViewHolder, position: Int) {
        holder.cityNameTV.text = ridesArray[position].getCity()
        holder.rideIdTV.text = ridesArray[position].getId().toString()
        holder.dateTv.text = ridesArray[position].getDate()
        holder.stateNameTV.text = ridesArray[position].getState()
        holder.originStationTv.text = ridesArray[position].getOrigin_station_code().toString()
        holder.distanceTV.text = ridesArray[position].getDistance().toString()
        holder.pathArrayTv.text = ridesArray[position].getStation_path().contentToString()
        Glide.with(holder.itemView.context).load(ridesArray[position].getMap_url().toString()).into(holder.mapImage)

    }

    override fun getItemCount(): Int {
        return ridesArray.size
    }

    class RideViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mapImage : ShapeableImageView = itemView.findViewById(R.id.map_imageview)
        val cityNameTV : TextView = itemView.findViewById(R.id.city_name_tv)
        val stateNameTV : TextView = itemView.findViewById(R.id.state_name_tv)
        val rideIdTV : TextView = itemView.findViewById(R.id.ride_id_tv)
        val originStationTv : TextView = itemView.findViewById(R.id.origin_station_tv)
        val pathArrayTv : TextView = itemView.findViewById(R.id.path_array_tv)
        val dateTv : TextView = itemView.findViewById(R.id.date_tv)
        val distanceTV : TextView = itemView.findViewById(R.id.distance_tv)

    }
}