package com.example.notanotherweatherapp.ui.compose.currentforecast

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.notanotherweatherapp.model.Clothing
import com.example.notanotherweatherapp.model.ClothingChange

@Composable
fun ClothingChangeLine(
    clothingChange: ClothingChange,
    isFirst: Boolean,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(start = 8.dp, end = 8.dp, bottom = 4.dp)
    ) {
        Icon(
            painter = painterResource(clothingChange.clothing.iconId),
            contentDescription = clothingChange.clothing.displayName,
            modifier = Modifier
                .size(24.dp)
        )
        Text(
            text = if (isFirst) "Now:" else "${clothingChange.time}:",
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(horizontal = 4.dp)
        )
        Text(
            text = clothingChange.clothing.conditionDescription,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ClothingLineChangePreview() {
    ClothingChangeLine(
        clothingChange = ClothingChange(
            clothing = Clothing.SCARF,
            time = "8pm",
            temperature = 76,
        ),
        isFirst = false,
    )
}