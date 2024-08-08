package com.example.myassignment.Data

import android.content.Context
import android.content.SharedPreferences


object SharedPrefrence {
    private const val PREFS_NAME = "city_data"
    private lateinit var sharedPreferences: SharedPreferences

    fun init(context: Context) {
        sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    fun setCityName (Name : String){
        with(sharedPreferences.edit() ){
            putString("city_name",Name)
            commit()
        }
    }
    fun getCityName(): String?
    {
        return sharedPreferences.getString("city_name", "" )
    }



    fun setCity_longitude(longitude : String)
    {
        with(sharedPreferences.edit()){
            putString("longitude", longitude)
            commit()
        }
    }
    fun getCity_longitude(): String?
    {
        return sharedPreferences.getString("longitude", "")
    }

    fun setCity_latitude(latitude : String)
    {
        with(sharedPreferences.edit() ){
            putString("latitude", latitude)
            commit()
        }
    }
    fun getCity_latitude(): String?
    {
        return sharedPreferences.getString("latitude", "")
    }
}
