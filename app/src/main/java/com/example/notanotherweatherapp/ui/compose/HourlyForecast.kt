package com.example.notanotherweatherapp.ui.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.notanotherweatherapp.R
import com.example.notanotherweatherapp.TEST_PERIOD
import com.example.notanotherweatherapp.getTimeFromDateString
import com.example.notanotherweatherapp.model.Period

@Composable
fun HourlyForecast(period: Period?, modifier: Modifier = Modifier) {
    Card(
        shape = RoundedCornerShape(topStart = 100.dp, bottomStart = 100.dp),
        colors = CardDefaults.cardColors(containerColor = Color.LightGray),
        elevation = CardDefaults.cardElevation(4.dp),
        modifier = modifier
            .height(48.dp)
            .fillMaxWidth()
            .padding(start = 8.dp, top = 8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_sunny),
                contentDescription = "Weather icon",
                contentScale = ContentScale.FillHeight,
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(4.dp)
            )
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.End,
                    modifier = Modifier
                        .fillMaxHeight()
                        .align(Alignment.CenterVertically)
                        .padding(end = 4.dp)
                ) {
                    Text(
                        text = getTimeFromDateString(period?.startTime ?: ""),
                        textAlign = TextAlign.End,
                        fontWeight = FontWeight.SemiBold,
                        style = MaterialTheme.typography.labelMedium,
                    )

                    val probabilityOfPrecipitation =
                        period?.probabilityOfPrecipitation?.value?.toInt() ?: 0

                    if (probabilityOfPrecipitation > 0) {
                        Text(
                            text = "$probabilityOfPrecipitation%",
                            textAlign = TextAlign.End,
                            style = MaterialTheme.typography.labelSmall,
                        )
                    }
                }
                Text(
                    text = "${period?.temperature ?: ""}°",
                    style = MaterialTheme.typography.labelLarge
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HourlyForecastPreview() {
    HourlyForecast(period = TEST_PERIOD, Modifier)
}