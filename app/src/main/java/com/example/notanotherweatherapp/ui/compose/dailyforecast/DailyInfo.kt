package com.example.notanotherweatherapp.ui.compose.dailyforecast

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.notanotherweatherapp.model.PeriodGroup

@Composable
fun DailyInfo(
    chunkedPeriods: List<List<PeriodGroup>>,
    selectedIndex: Int,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
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
                    .padding(8.dp)
            ) {
                if (chunkedPeriods.isNotEmpty() && chunkedPeriods[selectedIndex].size > 1) {
                    DailyInfoColumn(
                        periodGroup = chunkedPeriods[selectedIndex].first(),
                        labelText = "Today",
                        modifier = Modifier
                            .weight(1f)
                            .padding(end = 12.dp),
                    )
                } else {
                    DailyInfoColumn(
                        periodGroup = chunkedPeriods[selectedIndex].last(),
                        labelText = "Tonight",
                        modifier = Modifier
                            .weight(1f)
                    )
                }
            }
        }
    }
}