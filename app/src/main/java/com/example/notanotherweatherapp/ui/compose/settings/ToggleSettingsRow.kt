package com.example.notanotherweatherapp.ui.compose.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.notanotherweatherapp.Preference
import com.example.notanotherweatherapp.PreferenceManager
import com.example.notanotherweatherapp.noRippleClickable

@Composable
fun ToggleSettingsRow(
    preference: Preference,
    preferenceManager: PreferenceManager,
    onPreferenceChanged: (key: String, value: String?) -> Unit
) {
    var isFirst by remember {
        mutableStateOf(preferenceManager.getPreference(preference) ==
            preference.toggleChoices?.first?.key)
    }

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
                .weight(1F)
                .padding(end = 12.dp)
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
        ) {
            SettingsToggleBox(
                text = preference.toggleChoices?.first?.displayName ?: "",
                isSelected = isFirst,
                modifier = Modifier
                    .noRippleClickable {
                        if (!isFirst) isFirst = true
                        onPreferenceChanged(preference.key, preference.toggleChoices?.first?.key)
                    }
            )
            SettingsToggleBox(
                text = preference.toggleChoices?.second?.displayName ?: "",
                isSelected = !isFirst,
                modifier = Modifier
                    .noRippleClickable {
                        if (isFirst) isFirst = false
                        onPreferenceChanged(preference.key, preference.toggleChoices?.second?.key)
                    }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ToggleSettingsRowPreview() {
    ToggleSettingsRow(
        Preference.TEMPERATURE_UNIT,
        PreferenceManager(context = LocalContext.current)
    ) { _, _ -> }
}