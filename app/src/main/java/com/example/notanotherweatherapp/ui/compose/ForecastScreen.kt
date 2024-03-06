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
import androidx.compose.ui.graphics.Color
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
        (viewModel.hourlyGroups.isEmpty() || viewModel.cityString.isEmpty())
    ) {
        viewModel.getPeriodsAndClothing(
            currentLocation.latitude,
            currentLocation.longitude,
            context,
        )
    }

    if (viewModel.cityString.isEmpty() || viewModel.hourlyGroups.isEmpty()) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            CircularProgressIndicator(
                color = Color.LightGray,
                modifier = Modifier
                    .size(120.dp)
                    .align(Alignment.Center)
            )
        }
    } else {
        Column {
            CurrentForecast(
                periodGroup = viewModel.hourlyGroups.firstOrNull(),
                locationString = viewModel.cityString,
            )
            ClothingRow(
                clothing = Clothing.entries,
                activeClothing = viewModel.activeClothing,
                modifier = Modifier
                    .padding(top = 4.dp)
            )
            Row(modifier = Modifier.weight(1F)) {
                ForecastInfoBox(
                    period = viewModel.dailyGroups.firstOrNull()?.period,
                    modifier = Modifier
                        .weight(0.67F)
                        .padding(top = 4.dp, bottom = 4.dp),
                )
                LazyColumn(modifier = Modifier
                    .fillMaxSize()
                    .weight(0.33F)
                ) {
                    itemsIndexed(viewModel.hourlyGroups) { index, group ->
                        if (index != 0) HourlyForecast(
                            periodGroup = group,
                            modifier =
                                if (index == viewModel.hourlyGroups.lastIndex) {
                                    Modifier.padding(bottom = 4.dp)
                                } else Modifier
                        )
                    }
                }
            }
            DailyForecastRow(dailyGroups = viewModel.dailyGroups)
        }
    }
}