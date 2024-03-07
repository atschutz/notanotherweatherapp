package com.example.notanotherweatherapp.ui.compose.dailyforecast

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.example.notanotherweatherapp.model.PeriodGroup

@Composable
fun DailyInfoColumn(
    periodGroup: PeriodGroup,
    labelText: String,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = labelText,
            style = MaterialTheme.typography.labelSmall,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = periodGroup.period.detailedForecast ?: "",
            style = MaterialTheme.typography.bodySmall
        )
    }
}