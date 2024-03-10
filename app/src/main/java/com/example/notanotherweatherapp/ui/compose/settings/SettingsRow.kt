package com.example.notanotherweatherapp.ui.compose.settings

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.notanotherweatherapp.Preference
import com.example.notanotherweatherapp.PreferenceManager

@Composable
fun SettingsRow(preference: Preference, preferenceManager: PreferenceManager) {
    var textInput by remember { mutableStateOf(preferenceManager.getPreference(preference)) }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp, end = 8.dp, bottom = 4.dp)
    ) {
        Text(
            text = preference.displayName,
            style = MaterialTheme.typography.labelSmall,
            modifier = Modifier
                .padding(end = 12.dp)
        )
        BasicTextField(
            value = textInput,
            textStyle = MaterialTheme.typography.labelSmall.copy(textAlign = TextAlign.Center),
            singleLine = true,
            onValueChange = {
                textInput = it
                // If valid
                // preferenceManager.savePreference(preference, it)
            },
            decorationBox = {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .background(
                            color = Color.LightGray,
                            shape = RoundedCornerShape(8.dp)
                        )
                        .padding(4.dp)
                ) {
                    it()
                }
            },
            modifier = Modifier
                .width(52.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SettingsRowPreview() {
    SettingsRow(
        Preference.COMFY_TEMPERATURE,
        PreferenceManager(context = LocalContext.current)
    )
}