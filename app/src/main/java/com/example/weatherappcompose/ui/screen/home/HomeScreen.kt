package com.example.weatherappcompose.ui.screen.home

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.weatherappcompose.R
import com.example.weatherappcompose.data.model.LocationModel
import com.example.weatherappcompose.data.network.response.Forecast
import com.example.weatherappcompose.data.network.response.ForecastdayItem
import com.example.weatherappcompose.data.network.response.FullWeatherResponse
import com.example.weatherappcompose.ui.common.HomeUiState
import com.example.weatherappcompose.ui.component.ErrorScreen
import com.example.weatherappcompose.ui.component.ForecastListItem
import com.example.weatherappcompose.ui.component.LoadingScreen
import com.example.weatherappcompose.ui.component.ScreenSection
import com.example.weatherappcompose.ui.component.WeatherBox
import com.example.weatherappcompose.ui.theme.coolTemp
import com.example.weatherappcompose.ui.theme.hotTemp
import com.example.weatherappcompose.ui.theme.warmTemp

@Composable
fun HomeScreen(
    uiState: HomeUiState,
    locationModel: LocationModel,
    homeViewModel: HomeViewModel
) {
    if (locationModel.latitude != 0.0) {
        LaunchedEffect(Unit) {
            homeViewModel.getWeather("${locationModel.latitude},${locationModel.longitude}")
        }
    }
    when (uiState) {
        is HomeUiState.Loading -> LoadingScreen()
        is HomeUiState.Success -> HomeContent(
            weatherData = uiState.weatherResponse,
            locationDetail = locationModel,
            forecast = uiState.weatherResponse.forecast
        )

        is HomeUiState.Error -> {
            ErrorScreen(Modifier.clickable {
                    homeViewModel.getWeather("${locationModel.latitude},${locationModel.longitude}")
            })
        }
    }
    Log.d("HomeScreen", locationModel.toString())
}

@Composable
fun HomeContent(
    weatherData: FullWeatherResponse,
    locationDetail: LocationModel,
    modifier: Modifier = Modifier,
    forecast: Forecast
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = modifier.padding(top = 52.dp)
        ) {

            Image(
                painter = painterResource(id = R.drawable.pin_location),
                contentDescription = null
            )
            Text(
//                text = weatherData.location.name,
                text = locationDetail.village,
                fontSize = 34.sp,
                style = MaterialTheme.typography.titleMedium
            )
        }
        AsyncImage(
            model = ImageRequest.Builder(context = LocalContext.current)
                .crossfade(true)
                .data("http:${weatherData.current.condition.icon}")
                .build(),
            contentDescription = "condition icon",
            modifier = Modifier
                .size(124.dp)
                .padding(top = 32.dp)
        )
        Text(text = weatherData.current.condition.text, modifier = Modifier.padding(bottom = 16.dp))

        Text(
            text = weatherData.current.feelslikeC.toString() + "\u2103",
            fontSize = 34.sp,
            style = MaterialTheme.typography.titleLarge,
            color = when {
                weatherData.current.feelslikeC!! <= 24.0 -> coolTemp
                weatherData.current.feelslikeC in 25.0..30.0 -> warmTemp
                else -> hotTemp

            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {

            WeatherBox(
                key = "Humidity",
                value = weatherData.current.humidity.toString()
            )
            Spacer(modifier = Modifier.width(20.dp))
            WeatherBox(
                key = "UV",
                value = weatherData.current.uv.toString()
            )
        }
        ScreenSection(
            title = "Forecast Today",
            modifier = Modifier
                .padding(top = 62.dp)
                .padding(horizontal = 8.dp)
        ) {
            ForecastList(forecastDayItem = forecast.forecastday[0])
        }
        Spacer(modifier = Modifier.height(8.dp))
    }
}

@Composable
fun ForecastList(
    forecastDayItem: ForecastdayItem
) {
    LazyRow {
        items(forecastDayItem.hour) { item ->
            ForecastListItem(forecastDayHourlyItem = item)
        }
    }
}