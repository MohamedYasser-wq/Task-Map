package com.example.myassignment.Fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.myassignment.Data.SharedPrefrence
import com.example.myassignment.R
import com.example.myassignment.databinding.FragmentMapBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions


class MapFragment : Fragment(), OnMapReadyCallback {

    private lateinit var binding: FragmentMapBinding
    private var navController: NavController? = null
    private lateinit var googleMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMapBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

        val mapFragment = childFragmentManager.findFragmentById(R.id.map_fragment) as SupportMapFragment?
        mapFragment?.getMapAsync(this)
    }


    override fun onMapReady(map: GoogleMap) {
        googleMap = map


        val longitudeString = SharedPrefrence.getCity_longitude()
        val latitudeString = SharedPrefrence.getCity_latitude()


        if (!(longitudeString.equals(" ")&&latitudeString.equals(" "))) {
            val longitude = longitudeString!!.toDouble()
            val latitude = latitudeString!!.toDouble()
            val CityName = SharedPrefrence.getCityName() ?: "Unknown City"

            val location = LatLng(latitude, longitude)
            googleMap.addMarker(MarkerOptions().position(location).title(CityName))
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 10f))
        } else
        {
            Log.e("MapFragment", "Invalid coordinates: longitude or latitude is empty or null")
        }
    }

}
