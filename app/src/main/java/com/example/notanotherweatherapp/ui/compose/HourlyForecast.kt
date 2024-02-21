package com.example.notanotherweatherapp.ui.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.notanotherweatherapp.R
import com.example.notanotherweatherapp.TEST_PERIOD
import com.example.notanotherweatherapp.model.Period

@Composable
fun HourlyForecast(period: Period?, modifier: Modifier) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .height(50.dp)
            .fillMaxWidth()
            .padding(start = 8.dp)
            .background(
                color = Color.LightGray,
                shape = RoundedCornerShape(topStart = 100.dp, bottomStart = 100.dp)
            )
    ) {
        Row {
            Image(
                painter = painterResource(id = R.drawable.ic_sunny),
                contentDescription = "Weather icon",
                contentScale = ContentScale.FillHeight,
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(4.dp)
            )
            val probPrecip = period?.probabilityOfPrecipitation?.value?.toInt()
            Text(
                text =
                    if (probPrecip == null || probPrecip <= 0) ""
                    else "$probPrecip%",
                style = MaterialTheme.typography.labelSmall,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
            )
        }
        Text(
            text = "${period?.temperature ?: ""}°",
            style = MaterialTheme.typography.labelLarge
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HourlyForecastPreview() {
    HourlyForecast(period = TEST_PERIOD, Modifier)
}