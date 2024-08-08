package com.example.myassignment.Fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.assignment.Adapter.CityAdapter
import com.example.assignment.Data.CityItem
import com.example.myassignment.Data.SharedPrefrence
import com.example.myassignment.R
import com.example.myassignment.databinding.FragmentSearchBinding
import com.google.android.gms.maps.GoogleMap
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.InputStreamReader

class SearchFragment : Fragment(),CityAdapter.OnClick{
    private lateinit var binding: FragmentSearchBinding
    private var navController: NavController? = null
    private lateinit var cityAdapter: CityAdapter
    private lateinit var cities: List<CityItem>
    private lateinit var googleMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        navController = Navigation.findNavController(container!!)

        val recyclerView = binding.RecyclerCities

        //Load Data
        context?.assets?.open("cities.json")?.use { inputStream ->
            val reader = InputStreamReader(inputStream)
            cities = Gson().fromJson(reader, object : TypeToken<List<CityItem>>() {}.type)
        }
        //to Sort
        val sortedCities = cities.sortedWith(compareBy({ it.name }, { it.country }))

        cityAdapter = CityAdapter(sortedCities,this)
        recyclerView.adapter = cityAdapter

        //TextWatcher on EditText
        val searchInput: EditText = binding.searchInput
        searchInput.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val filteredCities = filterCities(sortedCities, s.toString())
                cityAdapter.updateData(filteredCities)
            }
            override fun afterTextChanged(s: Editable?) {}
        })

        return binding.root
    }

    private fun filterCities(cities: List<CityItem>, prefix: String): List<CityItem>
    {
        return cities.filter { it.name.startsWith(prefix, ignoreCase = true) }
    }

    override fun onClick(latitude: Double, longitude: Double, CityName: String)
    {
        SharedPrefrence.setCityName(CityName)
        SharedPrefrence.setCity_latitude(latitude.toString())
        SharedPrefrence.setCityName(longitude.toString())
        navController!!.navigate(R.id.action_searchFragment_to_mapFragment)
    }


}
