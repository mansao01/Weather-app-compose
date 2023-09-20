package com.example.weatherappcompose.ui.screen.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.weatherappcompose.WeatherApplication
import com.example.weatherappcompose.data.WeatherRepository
import com.example.weatherappcompose.ui.common.HomeUiState
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class HomeViewModel(
    private val weatherRepository: WeatherRepository
) : ViewModel() {
    var uiState: HomeUiState by mutableStateOf(HomeUiState.Loading)
        private set

     fun getWeather(location: String) {
        viewModelScope.launch {
            uiState = HomeUiState.Loading
            uiState = try {
                val result =
                    weatherRepository.getWeather("e8903efcc27a422ebfd100720231409", location)
                HomeUiState.Success(result)
            } catch (e: IOException) {
                HomeUiState.Error(e.message.toString())
            } catch (e: HttpException) {
                HomeUiState.Error(e.message.toString())
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application =
                    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as WeatherApplication)
                val weatherRepository = application.container.weatherRepository
                HomeViewModel(weatherRepository = weatherRepository)
            }
        }
    }
}