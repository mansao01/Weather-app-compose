package com.example.weatherappcompose.data.network.api

import com.example.weatherappcompose.data.network.response.FullWeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("forecast.json?")
    suspend fun getWeather(
        @Query("key")
        key: String,
        @Query("q")
        q: String
    ):FullWeatherResponse
}