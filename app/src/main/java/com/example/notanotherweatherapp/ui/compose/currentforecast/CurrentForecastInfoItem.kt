package com.example.notanotherweatherapp.ui.compose.currentforecast

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.notanotherweatherapp.R

@Composable
fun CurrentForecastInfoItem(
    modifier: Modifier = Modifier,
    title: String,
    headline: String? = null,
    body: String? = null,
    imageId: Int? = null,
    imageAngle: Float = 0F,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(4.dp)
            .background(
                color = Color.Gray,
                shape = RoundedCornerShape(
                    topStart = 16.dp,
                    topEnd = 16.dp,
                    bottomStart = 16.dp,
                    bottomEnd = 16.dp,
                )
            )
            .padding(8.dp)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.labelMedium,
            textAlign = TextAlign.Center,
            modifier = Modifier.align(Alignment.TopCenter)
        )
        imageId?.let {
            Image(
                painter = painterResource(id = it),
                contentDescription = "$title icon",
                modifier = Modifier
                    .size(56.dp)
                    .align(Alignment.Center)
                    .rotate(imageAngle)
            )
        }
        headline?.let {
            Text(
                text = it,
                style = MaterialTheme.typography.headlineLarge,
                textAlign = TextAlign.Center,
                modifier = Modifier.align(Alignment.Center)
            )
        }
        body?.let {
            Text(
                text = it,
                style = MaterialTheme.typography.labelMedium,
                textAlign = TextAlign.Center,
                modifier = Modifier.align(Alignment.BottomCenter)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CurrentForecastItemPreview() {
    Box(
        modifier = Modifier.size(120.dp)
    ) {
        CurrentForecastInfoItem(
            title = "Wind Direction",
            body = "SW",
            imageId = R.drawable.ic_arrow,
            imageAngle = 90F,
        )
    }
}