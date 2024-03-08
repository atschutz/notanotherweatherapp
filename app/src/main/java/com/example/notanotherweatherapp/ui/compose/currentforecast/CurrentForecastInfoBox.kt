package com.example.notanotherweatherapp.ui.compose.currentforecast

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.notanotherweatherapp.R
import com.example.notanotherweatherapp.TEST_CLOTHING_CHANGES
import com.example.notanotherweatherapp.TEST_PERIOD
import com.example.notanotherweatherapp.model.ClothingChange
import com.example.notanotherweatherapp.model.Period
import com.example.notanotherweatherapp.ui.compose.DailyRowSpacer
import java.time.LocalDate
import kotlin.math.roundToInt

@Composable
fun CurrentForecastInfoBox(period: Period, clothingChanges: List<ClothingChange>, modifier: Modifier = Modifier) {
    Card(
        shape = RoundedCornerShape(topEnd = 24.dp, bottomEnd = 24.dp),
        colors = CardDefaults.cardColors(containerColor = Color.LightGray),
        elevation = CardDefaults.cardElevation(4.dp),
        modifier = Modifier
            .fillMaxSize()
    ) {
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .weight(1F)
                .padding(
                    horizontal = 4.dp,
                )
        ) {
            item { Box(modifier = Modifier.padding(top = 8.dp)) }
            items(clothingChanges) {
                ClothingChangeLine(it)
            }
            item {
                val date = LocalDate.now()
                Row {
                    CurrentForecastInfoItem(
                        title = date.month.name,
                        headline = date.dayOfMonth.toString(),
                        body = date.dayOfWeek.name,
                        modifier = Modifier.weight(1F)
                    )
                    CurrentForecastInfoItem(
                        title = "PRECIPITATION",
                        headline = period.probabilityOfPrecipitation?.value?.roundToInt().toString(),
                        body = "PERCENT",
                        modifier = Modifier.weight(1F)
                    )
                }
            }
            item {
                Row {
                    CurrentForecastInfoItem(
                        title = "WIND SPEED",
                        headline = period.windSpeed?.filter { it.isDigit() },
                        body = "MPH",
                        modifier = Modifier.weight(1F),
                    )
                    CurrentForecastInfoItem(
                        title = "WIND DIRECTION",
                        imageId = R.drawable.ic_arrow,
                        imageAngle = windDirectionAngleMap[period.windDirection ?: "N"] ?: 0F,
                        body = windDirectionNameMap[period.windDirection],
                        modifier = Modifier.weight(1F)
                    )
                }
            }
            item {
                Row {
                    CurrentForecastInfoItem(
                        title = "HUMIDITY",
                        headline = period.relativeHumidity?.value?.toInt().toString(),
                        body = "PERCENT",
                        modifier = Modifier.weight(1F)
                    )
                    CurrentForecastInfoItem(
                        title = "DEWPOINT",
                        headline = period.dewpoint.value.roundToInt().toString() + "º",
                        body = "CELSIUS",
                        modifier = Modifier.weight(1F)
                    )
                }
            }
            item {
                DailyRowSpacer()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ForecastInfoBoxPreview() {
    CurrentForecastInfoBox(
        period = TEST_PERIOD,
        clothingChanges = TEST_CLOTHING_CHANGES,
        modifier = Modifier
    )
}

val windDirectionAngleMap = mapOf(
    "E" to 0F,
    "SE" to 45F,
    "S" to 90F,
    "SW" to 135F,
    "W" to 180F,
    "NW" to 225F,
    "N" to 270F,
    "NE" to 315F,
)

val windDirectionNameMap = mapOf(
    "E" to "EAST",
    "SE" to "SOUTHEAST",
    "S" to "SOUTH",
    "SW" to "SOUTHWEST",
    "W" to "WEST",
    "NW" to "NORTHWEST",
    "N" to "NORTH",
    "NE" to "NORTHEAST",
)