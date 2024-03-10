package com.example.notanotherweatherapp.ui.compose.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.notanotherweatherapp.Preference
import com.example.notanotherweatherapp.PreferenceManager
import com.example.notanotherweatherapp.R
import com.example.notanotherweatherapp.noRippleClickable

@Composable
fun SettingsMenu(
    preferenceManager: PreferenceManager,
    modifier: Modifier = Modifier,
    onBackClicked: () -> Unit
) {
    Column(
        modifier = modifier
            .width(IntrinsicSize.Max)
            .background(
                color = Color.Gray,
                shape = RoundedCornerShape(bottomStart = 8.dp)
            )
            .padding(bottom = 4.dp)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_arrow),
            contentDescription = "Back button",
            modifier = Modifier
                .padding(horizontal = 8.dp, vertical = 8.dp)
                .size(16.dp)
                .noRippleClickable { onBackClicked() }
        )
        Preference.entries.forEach {
            SettingsRow(
                preference = it,
                preferenceManager = preferenceManager
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SettingsMenuPreview() {
    SettingsMenu(
        preferenceManager = PreferenceManager(LocalContext.current)
    ) { }
}