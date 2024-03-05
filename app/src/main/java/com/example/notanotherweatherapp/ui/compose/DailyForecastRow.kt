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
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.notanotherweatherapp.R
import com.example.notanotherweatherapp.TEST_HOURLY_GROUP
import com.example.notanotherweatherapp.model.PeriodGroup
import com.example.notanotherweatherapp.safeSlice

@Composable
fun DailyForecastRow(dailyGroups: List<PeriodGroup>, modifier: Modifier = Modifier) {
    Card(
        shape = RoundedCornerShape(topStart = 24.dp),
        colors = CardDefaults.cardColors(containerColor = Color.LightGray),
        elevation = CardDefaults.cardElevation(4.dp),
        modifier = modifier
            .fillMaxWidth()
            .height(60.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(Color.Gray)
        )
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            if (dailyGroups.isNotEmpty()) {
                val chunkedPeriods = if (dailyGroups[0].period.isDaytime == true) {
                    // Do nothing.
                    dailyGroups.chunked(2)
                } else {
                    // Single chunk first period, remove last.
                    listOf(listOf(dailyGroups[0])) +
                    dailyGroups.slice(1..<dailyGroups.lastIndex).chunked(2)
                }
                chunkedPeriods.forEachIndexed { index, dailyGroupChunk ->
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
                        dayGroup = dailyGroupChunk.first(),
                        nightGroup = dailyGroupChunk.last(),
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
    dayGroup: PeriodGroup,
    nightGroup: PeriodGroup,
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        dayGroup.period.name?.let {
            Text(
                text =
                    if (dayGroup.period.number == 1) {
                        if (dayGroup.period.isDaytime == true) "Today"
                        else "Tonight"
                    } else it.safeSlice(0..2),
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Medium,
            )
        }
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            DailyForecastItemColumn(periodGroup = dayGroup)
            if (dayGroup != nightGroup) {
                Text(
                    text = "-",
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier
                        .padding(4.dp)
                )
                DailyForecastItemColumn(periodGroup = nightGroup)
            }
        }
    }
}

@Composable
fun DailyForecastItemColumn(periodGroup: PeriodGroup, modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Text(
            text = "${periodGroup.period.temperature}°",
            style = MaterialTheme.typography.bodySmall,
            fontWeight = FontWeight.Normal,
        )
        Image(
            painter = painterResource(
                id = periodGroup.weatherDisplay?.iconId ?: R.drawable.ic_question_mark
            ),
            contentDescription = "${periodGroup.period.shortForecast} icon",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .size(16.dp)
        )

        val probabilityOfPrecipitation =
            (periodGroup.period.probabilityOfPrecipitation?.value ?: 0.0).toInt()

        Text(
            text =
            if (probabilityOfPrecipitation > 0) "$probabilityOfPrecipitation%"
            else "",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodySmall,
            fontWeight = FontWeight.Normal,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DailyForecastRowPreview() {
    DailyForecastRow(
        dailyGroups = listOf(
            TEST_HOURLY_GROUP,
            TEST_HOURLY_GROUP,
            TEST_HOURLY_GROUP,
            TEST_HOURLY_GROUP,
            TEST_HOURLY_GROUP,
            TEST_HOURLY_GROUP,
            TEST_HOURLY_GROUP,
            TEST_HOURLY_GROUP,
            TEST_HOURLY_GROUP,
            TEST_HOURLY_GROUP,
            TEST_HOURLY_GROUP,
            TEST_HOURLY_GROUP,
            TEST_HOURLY_GROUP,
        )
    )
}

@Preview(showBackground = true)
@Composable
fun DailyForecastItemPreview() {
    DailyForecastItem(
        dayGroup= TEST_HOURLY_GROUP,
        nightGroup = TEST_HOURLY_GROUP,
    )
}