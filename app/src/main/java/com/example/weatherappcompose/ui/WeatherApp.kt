package com.example.weatherappcompose.ui

import androidx.compose.runtime.Composable
import com.example.weatherappcompose.data.model.LatLong
import com.example.weatherappcompose.ui.screen.home.HomeScreen

@Composable
fun WeatherApp(latLong: LatLong) {
    HomeScreen(latLong = latLong)
}