package com.example.notanotherweatherapp.ui.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.notanotherweatherapp.ui.ForecastScreenViewModel
import com.google.android.gms.maps.model.LatLng

@Composable
fun ForecastScreen(currentLocation: LatLng) {
    val viewModel: ForecastScreenViewModel = viewModel()

    viewModel.getPeriods(
        currentLocation.latitude,
        currentLocation.longitude
    )

    Column {
        if (viewModel.periods.isNotEmpty()) {
            CurrentForecast(period = viewModel.periods.first())
            Row(
                modifier = Modifier
                    .padding(top = 8.dp, bottom = 8.dp)
            ) {
                ForecastInfoBox(
                    period = viewModel.periods.first(),
                    modifier = Modifier.weight(0.7F)
                )
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(0.3F)
                ) {
                    viewModel.periods.forEachIndexed { index, period ->
                        if (index != 0) HourlyForecast(period = period, Modifier.weight(1F))
                    }
                }
            }
        }
    }
}