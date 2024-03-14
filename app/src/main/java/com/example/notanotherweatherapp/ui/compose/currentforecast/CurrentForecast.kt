package com.example.notanotherweatherapp.ui.compose.currentforecast

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.notanotherweatherapp.TEST_HOURLY_GROUP
import com.example.notanotherweatherapp.model.PeriodGroup

@Composable
fun CurrentForecast(periodGroup: PeriodGroup, locationString: String) {
    Card(
        shape = RoundedCornerShape(
            topStart = 0.dp,
            bottomStart = 0.dp,
            topEnd = 100.dp,
            bottomEnd = 100.dp
        ),
        colors = CardDefaults.cardColors(containerColor = Color.LightGray),
        elevation = CardDefaults.cardElevation(4.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(end = 8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text(
                text = "${periodGroup.period.temperature}°",
                style = MaterialTheme.typography.titleLarge
            )
            Column {
                Text(
                    text = locationString,
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                )
                Text(
                    text = periodGroup.period.shortForecast,
                    style = MaterialTheme.typography.titleSmall,
                    textAlign = TextAlign.Center,
                )
            }
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
            ) {
                Image(
                    painter =
                        painterResource(
                            id = periodGroup.weatherCondition.iconId
                        ),
                    contentDescription =
                        "${periodGroup.weatherCondition.displayName} icon",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.size(100.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CurrentForecastPreview() {
    CurrentForecast(periodGroup = TEST_HOURLY_GROUP, "New York, NY")
}