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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.notanotherweatherapp.model.Clothing

@Composable
fun ClothingRow(clothing: List<Clothing>, activeClothing: Set<Clothing>, modifier: Modifier = Modifier) {
    Card(
        shape = RectangleShape,
        colors = CardDefaults.cardColors(containerColor = Color.LightGray),
        elevation = CardDefaults.cardElevation(4.dp),
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(horizontal = 8.dp)
        ) {
            clothing.forEachIndexed { index, item ->
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
                    painter = painterResource(id = item.iconId),
                    contentDescription = "${item.displayName} icon",
                    contentScale = ContentScale.Fit,
                    alpha = if (activeClothing.contains(item)) 1.0F else 0.4F,
                    modifier = Modifier.weight(1F).padding(8.dp),
                )
            }
        }
    }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
            .background(Color.Gray)
    )
}

@Preview(showBackground = true)
@Composable
fun ClothingRowPreview() {
    ClothingRow(
        clothing = Clothing.entries,
        activeClothing = setOf(
            Clothing.TEE_SHIRT,
            Clothing.SWEATSHIRT,
            Clothing.UMBRELLA,
        )
    )
}