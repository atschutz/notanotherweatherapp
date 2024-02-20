package com.example.notanotherweatherapp.ui.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.notanotherweatherapp.TEST_PERIOD
import com.example.notanotherweatherapp.model.Period

@Composable
fun ForecastInfoBox(period: Period, modifier: Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(
                color = Color.LightGray,
                shape = RoundedCornerShape(topEnd = 24.dp, bottomEnd = 24.dp)
            )
    ) {

    }
}

@Preview(showBackground = true)
@Composable
fun ForecastInfoBoxPreview() {
    ForecastInfoBox(period = TEST_PERIOD, Modifier)
}