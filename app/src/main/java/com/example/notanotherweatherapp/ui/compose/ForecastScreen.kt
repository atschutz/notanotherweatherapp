package com.example.notanotherweatherapp.ui.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.notanotherweatherapp.model.Clothing
import com.example.notanotherweatherapp.ui.ForecastScreenViewModel
import com.google.android.gms.maps.model.LatLng

@Composable
fun ForecastScreen(currentLocation: LatLng?) {
    val viewModel: ForecastScreenViewModel = viewModel()
    val context = LocalContext.current

    if (currentLocation != null &&
        (viewModel.hourlyPeriods.isEmpty() || viewModel.cityString.isEmpty())
    ) {
        viewModel.getPeriodsAndClothing(
            currentLocation.latitude,
            currentLocation.longitude,
            context,
        )
    }

    if (viewModel.cityString.isEmpty() || viewModel.hourlyPeriods.isEmpty()) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            CircularProgressIndicator(
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .size(120.dp)
                    .align(Alignment.Center)
            )
        }
    } else {
        Column {
            CurrentForecast(
                period = viewModel.hourlyPeriods.firstOrNull(),
                locationString = viewModel.cityString,
            )
            ClothingRow(
                clothing = Clothing.entries,
                activeClothing = viewModel.activeClothing,
                modifier = Modifier
                    .padding(top = 8.dp)
            )
            Row(modifier = Modifier.weight(1F)) {
                ForecastInfoBox(
                    period = viewModel.hourlyPeriods.firstOrNull(),
                    modifier = Modifier
                        .weight(0.65F)
                        .padding(top = 8.dp, bottom = 8.dp),
                )
                LazyColumn(modifier = Modifier
                    .fillMaxSize()
                    .weight(0.35F)
                ) {
                    itemsIndexed(viewModel.hourlyPeriods) { index, period ->
                        if (index != 0) HourlyForecast(
                            period = period,
                            modifier =
                                if (index == viewModel.hourlyPeriods.lastIndex) {
                                    Modifier.padding(bottom = 8.dp)
                                } else Modifier
                        )
                    }
                }
            }
            DailyForecastRow(periods = viewModel.dailyPeriods)
        }
    }
}