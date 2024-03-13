package com.example.notanotherweatherapp.ui.compose.hourlyforecast

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp

@Composable
fun HourlyForecastDivider(
    text: String,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 8.dp, end = 4.dp)
    ) {
        Box(
            modifier = Modifier
                .height(1.dp)
                .weight(1F)
                .padding(start = 8.dp, end = 4.dp)
                .background(Color.LightGray)
        )
        Text(
            text = text,
            style = MaterialTheme.typography.labelSmall,
            color = Color.LightGray,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.End,
            letterSpacing = TextUnit(3.0F, TextUnitType.Sp),
            modifier = Modifier

        )
    }
}

@Preview(showBackground = true)
@Composable
fun HourlyForecastDividerPreview() {
    HourlyForecastDivider(text = "PREPARED")
}