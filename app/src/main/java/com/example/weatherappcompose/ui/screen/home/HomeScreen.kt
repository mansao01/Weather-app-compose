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
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.TextUnit
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
import com.example.weatherappcompose.ui.theme.good
import com.example.weatherappcompose.ui.theme.hazardous
import com.example.weatherappcompose.ui.theme.hotTemp
import com.example.weatherappcompose.ui.theme.moderate
import com.example.weatherappcompose.ui.theme.unhealthy
import com.example.weatherappcompose.ui.theme.unhealthyForSensitive
import com.example.weatherappcompose.ui.theme.veryUnhealthy
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
            Log.d("HomeScreen", uiState.message)
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
            color = when(weatherData.current.feelslikeC!!) {
                in -10000.0.. 22.0 -> coolTemp
                in 23.0..30.0 -> warmTemp
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
                value = weatherData.current.humidity.toString(),
                textColor = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.width(20.dp))
            WeatherBox(
                key = "UV",
                value = weatherData.current.uv.toString(),
                textColor = MaterialTheme.colorScheme.primary

            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {

            WeatherBox(
                key = "PM2.5",
                value = weatherData.current.airQuality?.pm25.toString(),
                textColor = when (weatherData.current.airQuality?.pm25!!) {
                    in 0.0..12.0 -> good
                    in 13.0..35.0 -> moderate
                    in 36.0..55.0 -> unhealthyForSensitive
                    in 56.0..150.0 -> unhealthy
                    in 150.0..250.0 -> veryUnhealthy
                    else -> hazardous
                }

            )
            Spacer(modifier = Modifier.width(20.dp))
            WeatherBox(
                key = "CO",
                value = weatherData.current.airQuality.co.toString(),
                textColor = MaterialTheme.colorScheme.primary

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
        Spacer(modifier = Modifier.height(16.dp))
        HyperlinkText(fullText = "Powered by WeatherApi.com", linkText = listOf("WeatherApi.com"))
        Spacer(modifier = Modifier.height(22.dp))

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

@Composable
fun HyperlinkText(
    modifier: Modifier = Modifier,
    fullText: String,
    linkText: List<String>,
    linkTextColor: Color = MaterialTheme.colorScheme.primary,
    linkTextFontWeight: FontWeight = FontWeight.Medium,
    linkTextDecoration: TextDecoration = TextDecoration.Underline,
    hyperlinks: List<String> = listOf("https://www.weatherapi.com/"),
    fontSize: TextUnit = TextUnit.Unspecified
) {
    val annotatedString = buildAnnotatedString {
        append(fullText)
        linkText.forEachIndexed { index, link ->
            val startIndex = fullText.indexOf(link)
            val endIndex = startIndex + link.length
            addStyle(
                style = SpanStyle(
                    color = linkTextColor,
                    fontSize = fontSize,
                    fontWeight = linkTextFontWeight,
                    textDecoration = linkTextDecoration
                ),
                start = startIndex,
                end = endIndex
            )
            addStringAnnotation(
                tag = "URL",
                annotation = hyperlinks[index],
                start = startIndex,
                end = endIndex
            )
        }
        addStyle(
            style = SpanStyle(
                fontSize = fontSize
            ),
            start = 0,
            end = fullText.length

        )
    }

    val uriHandler = LocalUriHandler.current

    ClickableText(
        modifier = modifier,
        text = annotatedString,
        onClick = {
            annotatedString
                .getStringAnnotations("URL", it, it)
                .firstOrNull()?.let { stringAnnotation ->
                    uriHandler.openUri(stringAnnotation.item)
                }
        },
    )
}