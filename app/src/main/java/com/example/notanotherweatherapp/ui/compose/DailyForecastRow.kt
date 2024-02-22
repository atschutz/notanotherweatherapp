package com.example.notanotherweatherapp.ui.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.notanotherweatherapp.R
import com.example.notanotherweatherapp.TEST_PERIOD
import com.example.notanotherweatherapp.model.Period
import com.example.notanotherweatherapp.safeSlice

@Composable
fun DailyForecastRow(periods: List<Period>, modifier: Modifier = Modifier) {
    // TODO Use grid data from API instead of all this!!!
    val isLastItemNight =
        periods.last().name?.contains("Night", ignoreCase = true) ?: true

    Card(
        shape = RoundedCornerShape(topStart = 24.dp),
        colors = CardDefaults.cardColors(containerColor = Color.LightGray),
        elevation = CardDefaults.cardElevation(4.dp),
        modifier = modifier
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(Color.Gray)
        )
        Card(
            shape = RectangleShape,
            colors = CardDefaults.cardColors(containerColor = Color.LightGray),
            elevation = CardDefaults.cardElevation(4.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                val chunkedPeriods = if (isLastItemNight) {
                    // Do nothing.
                    periods.chunked(2)
                } else {
                    // Single chunk first period, remove last.
                    listOf(listOf(periods[0])) +
                    periods.slice(1..<periods.lastIndex).chunked(2)
                }
                chunkedPeriods.forEachIndexed { index, period ->
                    if (index != 0) {
                        Box(
                            modifier = Modifier
                                .padding(vertical = 8.dp)
                                .background(Color.Gray)
                                .width(1.dp)
                                .fillMaxSize()
                        )
                    }
                    DailyForecastItem(
                        dayPeriod = period.first(),
                        nightPeriod = period.last(),
                        modifier = Modifier
                            .weight(1F)
                    )
                }
            }
        }
    }
}

@Composable
fun DailyForecastItem(
    dayPeriod: Period,
    nightPeriod: Period,
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.padding(4.dp)
    ) {
        dayPeriod.name?.let {
            Text(
                text = it.safeSlice(0..2),
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Medium,
            )
        }
        Text(
            text =
                if (dayPeriod.name == nightPeriod.name) "${nightPeriod.temperature}°"
                else "${nightPeriod.temperature}° - ${dayPeriod.temperature}°",
            style = MaterialTheme.typography.bodySmall,
            fontWeight = FontWeight.Normal,
        )
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_sunny),
                // TODO icon will vary, need to handle.
                contentDescription = "${dayPeriod.shortForecast} icon",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .padding(end = 4.dp)
            )

            val probabilityOfPrecipitation =
                (dayPeriod.probabilityOfPrecipitation?.value ?: 0.0)
                    .coerceAtLeast(
                        nightPeriod.probabilityOfPrecipitation?.value ?: 0.0
                    ).toInt()

            if (probabilityOfPrecipitation > 0) {
                Text(
                    text = probabilityOfPrecipitation.toString(),
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.Normal,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DailyForecastRowPreview() {
    DailyForecastRow(
        periods = listOf(
            TEST_PERIOD,
            TEST_PERIOD,
            TEST_PERIOD,
            TEST_PERIOD,
            TEST_PERIOD,
            TEST_PERIOD,
            TEST_PERIOD,
            TEST_PERIOD,
            TEST_PERIOD,
            TEST_PERIOD,
            TEST_PERIOD,
            TEST_PERIOD,
            TEST_PERIOD,
            TEST_PERIOD,
            TEST_PERIOD,
            TEST_PERIOD,
        )
    )
}

@Preview(showBackground = true)
@Composable
fun DailyForecastItemPreview() {
    DailyForecastItem(
        dayPeriod = TEST_PERIOD,
        nightPeriod = TEST_PERIOD
    )
}