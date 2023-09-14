package com.example.weatherappcompose.data

import com.example.weatherappcompose.data.network.api.ApiService
import com.example.weatherappcompose.data.network.response.FullWeatherResponse

interface WeatherRepository {
    suspend fun getWeather(key: String, q: String):FullWeatherResponse
}

class NetworkWeatherRepository(
    private val apiService: ApiService
) : WeatherRepository {
    override suspend fun getWeather(key: String, q: String) = apiService.getWeather(key, q)
}