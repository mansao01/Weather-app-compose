package com.example.weatherappcompose.data.model

data class LocationModel(
    val latitude: Double,
    val longitude: Double,
    val address: String,
    val city: String,
    val state: String,
    val country: String,
    val subCity:String,
    val village:String
)
