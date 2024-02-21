package com.example.notanotherweatherapp.ui.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.notanotherweatherapp.R

@Composable
fun ClothingRow(clothingMap: Map<String, Int>, modifier: Modifier) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(Color.LightGray)
            .padding(horizontal = 8.dp)
    ) {
        clothingMap.onEachIndexed { index, entry ->
            if (index != 0) {
                Box(
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                        .background(Color.Gray)
                        .width(1.dp)
                        .fillMaxSize()
                )
            }
            Image(
                painter = painterResource(id = entry.value),
                contentDescription = "${entry.key} icon",
                contentScale = ContentScale.Fit,
                modifier = Modifier.weight(1F),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ClothingRowPreview() {
    val map = mapOf(
        "Gloves" to R.drawable.ic_sunny,
        "Boots" to R.drawable.ic_sunny,
        "Short sleeve shirt" to R.drawable.ic_sunny,
        "Long Sleeve shirt" to R.drawable.ic_sunny,
        "Shorts" to R.drawable.ic_sunny,
        "Pants" to R.drawable.ic_sunny,
        "Jacket" to R.drawable.ic_sunny,
        "Umbrella" to R.drawable.ic_sunny,
    )
ClothingRow(clothingMap = map, Modifier)
}