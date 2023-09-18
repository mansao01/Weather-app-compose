package com.example.weatherappcompose.ui.component


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.weatherappcompose.data.network.response.HourItem

@Composable
fun ForecastListItem(
    forecastDayHourlyItem: HourItem,
    modifier: Modifier = Modifier
) {
    val dateTime = forecastDayHourlyItem.time
    val time = dateTime.substring(11)
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.onPrimary
        ),
        modifier = modifier
            .size(width = 120.dp, height = 164.dp)
            .padding(end = 4.dp)
            .shadow(
                elevation = 1.dp,
                shape = RoundedCornerShape(size = 4.dp)
            )
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {

            Text(text = time, modifier = Modifier.padding(top = 12.dp).padding(bottom = 12.dp))
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data("https:${forecastDayHourlyItem.condition.icon}")
                    .build(),
                contentDescription = null,
                modifier = Modifier.size(82.dp)
            )
            Text(text = forecastDayHourlyItem.feelslikeC.toString() + "\u2103")

        }
    }
}