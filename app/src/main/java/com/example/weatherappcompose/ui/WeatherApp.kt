package com.example.weatherappcompose.ui

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.weatherappcompose.data.model.LocationModel
import com.example.weatherappcompose.ui.screen.home.HomeScreen
import com.example.weatherappcompose.ui.screen.home.HomeViewModel

@Composable
fun WeatherApp(locationModel: LocationModel) {
    val homeViewModel: HomeViewModel = viewModel(factory = HomeViewModel.Factory)
    HomeScreen(uiState = homeViewModel.uiState, locationModel = locationModel, homeViewModel = homeViewModel)
}