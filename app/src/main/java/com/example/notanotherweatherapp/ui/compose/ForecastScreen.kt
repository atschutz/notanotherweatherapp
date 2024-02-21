package com.example.notanotherweatherapp.ui.compose

import android.location.Geocoder
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.notanotherweatherapp.TEST_CLOTHING_MAP
import com.example.notanotherweatherapp.ui.ForecastScreenViewModel
import com.google.android.gms.maps.model.LatLng

@Composable
fun ForecastScreen(currentLocation: LatLng?) {
    val viewModel: ForecastScreenViewModel = viewModel()
    val context = LocalContext.current

    if (currentLocation != null &&
        (viewModel.periods.isEmpty() || viewModel.cityString.isEmpty())
    ) {
        viewModel.getPeriods(
            currentLocation.latitude,
            currentLocation.longitude,
            context,
        )
    }

    if (viewModel.cityString.isEmpty() || viewModel.periods.isEmpty()) {
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
                period = viewModel.periods.firstOrNull(),
                locationString = viewModel.cityString,
            )
            ClothingRow(
                clothingMap = TEST_CLOTHING_MAP,
                modifier = Modifier
                    .padding(top = 8.dp)
            )
            Row(
                modifier = Modifier
                    .padding(top = 8.dp, bottom = 8.dp)
            ) {
                ForecastInfoBox(
                    period = viewModel.periods.firstOrNull(),
                    modifier = Modifier.weight(0.65F),
                )
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(0.35F)
                ) {
                    viewModel.periods.forEachIndexed { index, period ->
                        if (index != 0) HourlyForecast(period = period, Modifier.weight(1F))
                    }
                }
            }
        }
    }
}