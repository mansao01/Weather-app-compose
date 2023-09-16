package com.example.weatherappcompose.ui.screen.home

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.weatherappcompose.data.model.LocationModel
import com.example.weatherappcompose.data.network.response.FullWeatherResponse
import com.example.weatherappcompose.ui.common.HomeUiState
import com.example.weatherappcompose.ui.component.LoadingScreen

@Composable
fun HomeScreen(
    uiState: HomeUiState,
    locationModel: LocationModel,
    homeViewModel: HomeViewModel
) {
    val context = LocalContext.current
    if (locationModel.latitude != 0.0) {
        LaunchedEffect(Unit) {
            homeViewModel.getWeather("${locationModel.latitude},${locationModel.longitude}")
        }
    }
    when (uiState) {
        is HomeUiState.Loading -> LoadingScreen()
        is HomeUiState.Success -> HomeContent(
            weatherData = uiState.weatherResponse,
            locationDetail = locationModel
        )

        is HomeUiState.Error -> Toast.makeText(context, uiState.message, Toast.LENGTH_SHORT).show()
    }
    Log.d("HomeScreen", locationModel.toString())
}

@Composable
fun HomeContent(
    weatherData: FullWeatherResponse,
    locationDetail: LocationModel,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = modifier.padding(top = 52.dp)
        ) {
            Icon(
                imageVector = Icons.Default.LocationOn,
                contentDescription = null,
                Modifier
                    .size(26.dp)
                    .alignByBaseline()
            )
            Text(
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
            modifier = Modifier.size(104.dp).padding(top= 32.dp)
        )
        Text(
            text = weatherData.current.feelslikeC.toString() + "\u2103",
            fontSize = 34.sp,
            style = MaterialTheme.typography.titleLarge
        )
        Log.d("suhu", "http:${weatherData.current.condition.icon}")
    }

}