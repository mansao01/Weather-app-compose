package com.example.weatherappcompose.ui.common

import com.example.weatherappcompose.data.network.response.FullWeatherResponse

sealed interface HomeUiState {
    object Loading : HomeUiState
    data class Success(val weatherResponse: FullWeatherResponse):HomeUiState
    data class Error(val message:String) : HomeUiState
}
