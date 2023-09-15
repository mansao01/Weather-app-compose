package com.example.weatherappcompose.ui.screen.home

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.weatherappcompose.data.model.LocationModel
import com.example.weatherappcompose.data.network.response.FullWeatherResponse
import com.example.weatherappcompose.ui.common.HomeUiState

@Composable
fun HomeScreen(
    uiState: HomeUiState,
    locationModel: LocationModel,
    homeViewModel: HomeViewModel
) {
    if (locationModel.latitude != 0.0){
        LaunchedEffect(Unit) {
            homeViewModel.getWeather("${locationModel.latitude},${locationModel.longitude}")
        }
    }

    when (uiState) {
        is HomeUiState.Loading -> Text(text = "Please wait")
        is HomeUiState.Success -> HomeContent(weatherData = uiState.weatherResponse)
        is HomeUiState.Error -> Log.d("HomeError", uiState.message)
    }
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Latitude : " + locationModel.latitude)
        Text(text = "Longitude : " + locationModel.longitude)
    }
    Log.d("HomeScreen", locationModel.toString())
}

@Composable
fun HomeContent(
    weatherData: FullWeatherResponse
) {
    Text(text = weatherData.location.name)
}