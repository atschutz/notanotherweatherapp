package com.example.notanotherweatherapp.ui.compose.dailyforecast

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.notanotherweatherapp.R
import com.example.notanotherweatherapp.model.ConditionType
import com.example.notanotherweatherapp.model.PeriodGroup

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
                id = periodGroup.weatherCondition?.iconId ?: R.drawable.ic_question_mark
            ),
            contentDescription = "${periodGroup.period.shortForecast} icon",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .size(16.dp)
        )
        Text(
            text =
                if (periodGroup.weatherCondition?.conditionType == ConditionType.PRECIPITATION)
                    "${(periodGroup.period.probabilityOfPrecipitation?.value ?: 0.0).toInt()}%"
                else "",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodySmall,
            fontWeight = FontWeight.Normal,
        )
    }
}