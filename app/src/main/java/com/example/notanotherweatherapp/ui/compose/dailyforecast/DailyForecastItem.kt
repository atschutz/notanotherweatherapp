package com.example.notanotherweatherapp.ui.compose.dailyforecast

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.notanotherweatherapp.model.PeriodGroup
import com.example.notanotherweatherapp.noRippleClickable
import com.example.notanotherweatherapp.safeSlice

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
            .fillMaxWidth()
            .background(
                color = Color.Gray,
                shape = RoundedCornerShape(
                    topStart = 16.25.dp,
                    topEnd = 16.25.dp
                )
            )
            .padding(top = 1.dp, start = 0.25.dp, end = 0.25.dp)
            .background(
                color = if (index == selectedIndex) Color.Gray else Color.LightGray,
                shape = RoundedCornerShape(
                    topStart = 16.dp,
                    topEnd = 16.dp,
                    bottomStart = if (selectedIndex == index - 1) 8.dp else 0.dp,
                    bottomEnd = if (selectedIndex == index + 1) 8.dp else 0.dp,
                )
            )
            .noRippleClickable {
                onClick()
            }
    ) {
        Text(
            text =
            if (dayGroup.period.number == 1) {
                if (dayGroup.period.isDaytime) "Today"
                else "Tonight"
            } else dayGroup.period.name.safeSlice(0..2),
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Medium,
        )
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