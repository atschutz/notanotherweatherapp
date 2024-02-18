package com.example.notanotherweatherapp.ui.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.notanotherweatherapp.ui.ForecastScreenViewModel

@Composable
fun ForecastScreen() {
    val viewModel: ForecastScreenViewModel = viewModel()

    Column {
        Text(text = "Forecast!!!")
        viewModel.periods.forEach {
            Column {
                Text(text = it.number.toString())
                Text(text = it.shortForecast ?: "")
                Text(text = it.temperature.toString())
            }

        }
    }
}