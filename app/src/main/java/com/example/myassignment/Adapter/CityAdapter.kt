package com.example.assignment.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment.Data.CityItem
import com.example.myassignment.R

class CityAdapter(private var cities: List<CityItem>, private val listener: OnClick) : RecyclerView.Adapter<CityAdapter.CityViewHolder>() {

    interface OnClick {
        fun onClick(latitude: Double, longitude: Double, cityName: String)
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.city_card, parent, false)
        return CityViewHolder(view)
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        val city = cities[position]
        val coord = city.coord

        holder.cityName.text = "${city.name}, ${city.country}"

        if (coord != null) {
            val latitude = coord.lat
            val longitude = coord.lon
            holder.cityCoordinates.text = "Lat: $latitude, Lon: $longitude"

            holder.itemView.setOnClickListener {
                listener.onClick(latitude, longitude, city.name)
            }
        }
        else
        {
            holder.cityCoordinates.text = "Lat: Unknown, Lon: Unknown"

            holder.itemView.setOnClickListener {
                Toast.makeText(holder.itemView.context, "Location coordinates are not available", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun getItemCount(): Int = cities.size

    fun updateData(newCities: List<CityItem>) {
        cities = newCities
        notifyDataSetChanged()
    }

    class CityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cityName: TextView = itemView.findViewById(R.id.cityName)
        val cityCoordinates: TextView = itemView.findViewById(R.id.cityCoordinates)
    }
}
