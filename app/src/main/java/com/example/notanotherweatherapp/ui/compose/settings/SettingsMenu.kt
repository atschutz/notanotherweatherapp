package com.example.notanotherweatherapp.ui.compose.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.notanotherweatherapp.Preference
import com.example.notanotherweatherapp.PreferenceManager
import com.example.notanotherweatherapp.PreferenceType
import com.example.notanotherweatherapp.R
import com.example.notanotherweatherapp.model.ForecastRepository
import com.example.notanotherweatherapp.model.ForecastWebService
import com.example.notanotherweatherapp.noRippleClickable
import com.example.notanotherweatherapp.ui.ForecastScreenViewModel
import com.example.notanotherweatherapp.ui.ForecastScreenWorker

@Composable
fun SettingsMenu(
    viewModel: ForecastScreenViewModel,
    modifier: Modifier = Modifier,
    onBackClicked: (shouldRefresh: Boolean) -> Unit,
) {
    val preferenceMap = remember {
        mutableStateMapOf<String, String>()
    }

    Column(
        modifier = modifier
            .width(IntrinsicSize.Max)
            .background(
                color = Color.Gray,
                shape = RoundedCornerShape(bottomStart = 8.dp)
            )
            .padding(bottom = 4.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 8.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_arrow),
                contentDescription = "Back button",
                modifier = Modifier
                    .size(16.dp)
                    .noRippleClickable {
                        onBackClicked(false)
                    }
            )

            Icon(
                painter = painterResource(id = R.drawable.ic_check),
                contentDescription = "Back button",
                modifier = Modifier
                    .size(16.dp)
                    .noRippleClickable {
                        preferenceMap.forEach {
                            viewModel.preferenceManager.savePreference(it.key, it.value)
                        }
                        onBackClicked(true)
                    }
            )
        }
        Preference.entries.forEach {
            if (it.preferenceType == PreferenceType.INPUT) {
                InputSettingsRow(
                    preference = it,
                    preferenceManager = viewModel.preferenceManager,
                ) { value ->
                    preferenceMap[it.key] = value
                }
            } else {
                ToggleSettingsRow(
                    preference = it,
                    preferenceManager = viewModel.preferenceManager,
                    onPreferenceChanged = { key, value ->
                        preferenceMap[key] = value ?: ""
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SettingsMenuPreview() {
    SettingsMenu(
        viewModel = ForecastScreenViewModel(
            ForecastRepository(ForecastWebService()),
            ForecastScreenWorker(),
            PreferenceManager(LocalContext.current)
        )
    ) { }
}