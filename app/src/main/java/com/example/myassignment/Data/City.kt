package com.example.assignment.Data

data class CityItem(
    val id: Int,
    val name: String,
    val country: String,
    val coord: Coord?
)

data class Coord(
    val lat: Double,
    val lon: Double
)