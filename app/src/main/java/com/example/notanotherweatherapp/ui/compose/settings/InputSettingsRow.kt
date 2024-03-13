package com.example.notanotherweatherapp.ui.compose.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.notanotherweatherapp.Preference
import com.example.notanotherweatherapp.PreferenceManager

@Composable
fun InputSettingsRow(
    preference: Preference,
    preferenceManager: PreferenceManager,
    onValueChanged: (value: String) -> Unit,
) {
    var textInput by remember { mutableStateOf(preferenceManager.getPreference(preference)) }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .padding(start = 8.dp, end = 8.dp, bottom = 4.dp)
    ) {
        Text(
            text = preference.displayName,
            style = MaterialTheme.typography.labelSmall,
            modifier = Modifier
                .weight(0.75F)
                .padding(end = 12.dp)
        )
        BasicTextField(
            value = textInput,
            textStyle = MaterialTheme.typography.labelSmall.copy(textAlign = TextAlign.Center),
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            onValueChange = {
                textInput = it
                onValueChanged(it)
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
                .weight(0.25F)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SettingsRowPreview() {
    InputSettingsRow(
        Preference.COMFY_TEMPERATURE,
        PreferenceManager(context = LocalContext.current)
    ) { }
}