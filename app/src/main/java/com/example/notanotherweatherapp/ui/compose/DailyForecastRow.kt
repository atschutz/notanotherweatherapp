package com.example.notanotherweatherapp.ui.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.notanotherweatherapp.R
import com.example.notanotherweatherapp.TEST_HOURLY_GROUP
import com.example.notanotherweatherapp.model.PeriodGroup
import com.example.notanotherweatherapp.noRippleClickable
import com.example.notanotherweatherapp.safeSlice

// TODO - Overlap hourly list.

@Composable
fun DailyForecastRow(dailyGroups: List<PeriodGroup>, modifier: Modifier = Modifier) {
    var selectedIndex by remember { mutableIntStateOf(0) }

    Card(
        shape = RectangleShape,
        colors = CardDefaults.cardColors(containerColor = Color.Transparent),
        elevation = CardDefaults.cardElevation(4.dp),
        modifier = modifier
            .fillMaxWidth()
            .height(120.dp)
    ) {
        val chunkedPeriods =
            if (dailyGroups.isNotEmpty()) {
                if (dailyGroups[0].period.isDaytime == true) {
                    // Do nothing.
                    dailyGroups.chunked(2)
                } else {
                    // Single chunk first period, remove last.
                    listOf(listOf(dailyGroups[0])) +
                            dailyGroups.slice(1..<dailyGroups.lastIndex).chunked(2)
                }
            } else listOf()

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1F)
                .background(Color.Transparent)
        ){
            chunkedPeriods.forEachIndexed { index, dailyGroupChunk ->
                DailyForecastItem(
                    dayGroup = dailyGroupChunk.first(),
                    nightGroup = dailyGroupChunk.last(),
                    index = index,
                    selectedIndex = selectedIndex,
                    modifier = Modifier
                        .weight(1F)
                ) { selectedIndex = index }
            }
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1F)
                .background(Color.Gray)
                .padding(start = 0.5.dp, end = 0.5.dp)
                .background(Color.LightGray)
                .background(
                    color = Color.Gray,
                    shape = RoundedCornerShape(
                        topStart = if (selectedIndex == 0) 0.dp else 8.dp,
                        topEnd = if (selectedIndex == chunkedPeriods.lastIndex) 0.dp else 8.dp,
                    )
                )
        ) {
            item {
                // TODO - Get out of lazy column and add swipe to toggle today/tonight.
                Row(
                    modifier = Modifier
                        .padding(4.dp)
                ) {
                    if (chunkedPeriods[selectedIndex].size > 1) {
                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .padding(end = 12.dp)
                        ) {
                            Text(
                                text = "Today",
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = chunkedPeriods[selectedIndex].first().period.detailedForecast ?: "",
                                style = MaterialTheme.typography.bodySmall
                            )
                        }
                    }
                    Column(
                        modifier = Modifier
                            .weight(1f)
                    ) {
                        Text(
                            text = "Tonight",
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = chunkedPeriods[selectedIndex].last().period.detailedForecast ?: "",
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun DailyForecastItem(
    dayGroup: PeriodGroup,
    nightGroup: PeriodGroup,
    index: Int,
    selectedIndex: Int,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .background(
                color = Color.Gray,
                shape = RoundedCornerShape(
                    topStart = 9.dp,
                    topEnd = 9.dp
                )
            )
            .padding(top = 1.dp, start = 0.5.dp, end = 0.5.dp)
            .background(
                color = if (index == selectedIndex) Color.Gray else Color.LightGray,
                shape = RoundedCornerShape(
                    topStart = 8.dp,
                    topEnd = 8.dp,
                    bottomStart = if (selectedIndex == index - 1) 8.dp else 0.dp,
                    bottomEnd = if (selectedIndex == index + 1) 8.dp else 0.dp,
                )
            )
            .noRippleClickable {
                onClick()
            }
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