package com.example.notanotherweatherapp.ui.compose.dailyforecast

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
        val chunkedPeriods = getChunkedPeriodGroups(dailyGroups)

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
        if (chunkedPeriods.isNotEmpty()) {
            DailyInfo(
                chunkedPeriods = chunkedPeriods,
                selectedIndex = selectedIndex,
                modifier = Modifier.weight(1F)
            )
        }
    }
}

fun getChunkedPeriodGroups(periodGroups: List<PeriodGroup>): List<List<PeriodGroup>> =
    if (periodGroups.isNotEmpty()) {
        if (periodGroups[0].period.isDaytime == true) {
            // Do nothing.
            periodGroups.chunked(2)
        } else {
            // Single chunk first period, remove last.
            listOf(listOf(periodGroups[0])) +
                    periodGroups.slice(1..<periodGroups.lastIndex).chunked(2)
        }
    } else listOf()

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