package com.example.notanotherweatherapp.ui.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.notanotherweatherapp.R
import com.example.notanotherweatherapp.model.Clothing
import com.example.notanotherweatherapp.noRippleClickable
import com.example.notanotherweatherapp.ui.ForecastScreenViewModel
import com.example.notanotherweatherapp.ui.compose.currentforecast.CurrentForecast
import com.example.notanotherweatherapp.ui.compose.currentforecast.CurrentForecastInfoBox
import com.example.notanotherweatherapp.ui.compose.dailyforecast.DailyForecastRow
import com.example.notanotherweatherapp.ui.compose.hourlyforecast.HourlyForecast
import com.example.notanotherweatherapp.ui.compose.hourlyforecast.HourlyForecastDivider
import com.example.notanotherweatherapp.ui.compose.settings.SettingsMenu
import com.google.android.gms.maps.model.LatLng

@Composable
fun ForecastScreen(
    currentLocation: LatLng?,
) {
    val viewModel: ForecastScreenViewModel = viewModel()

    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current

    val focusRequester = remember { FocusRequester() }
    var showSettings by remember { mutableStateOf(false) }
    var dailyRowHeight by remember { mutableStateOf(0.dp) }

    // TODO - Use flow.
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
        Box(
            modifier = Modifier
                .fillMaxSize()
                .focusRequester(focusRequester)
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_settings),
                contentDescription = "Settings icon",
                tint = Color.Gray,
                modifier = Modifier
                    .padding(end = 4.dp, top = 2.dp)
                    .size(16.dp)
                    .align(Alignment.TopEnd)
                    .noRippleClickable { showSettings = true }
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Box(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    CurrentForecast(
                        periodGroup = viewModel.hourlyGroups.first(),
                        locationString = viewModel.cityString,
                    )
                    Icon(
                        painter = painterResource(R.drawable.ic_settings),
                        contentDescription = "Settings icon",
                        tint = Color.Gray,
                        modifier = Modifier
                            .padding(end = 4.dp, top = 2.dp)
                            .size(16.dp)
                            .align(Alignment.TopEnd)
                            .noRippleClickable { showSettings = true }
                    )
                    Icon(
                        painter = painterResource(R.drawable.ic_refresh),
                        contentDescription = "Refresh icon",
                        tint = Color.Gray,
                        modifier = Modifier
                            .padding(end = 4.dp, top = 2.dp)
                            .size(16.dp)
                            .align(Alignment.BottomEnd)
                            .noRippleClickable {
                                currentLocation?.let {
                                    viewModel.refresh(
                                        it.latitude,
                                        it.longitude,
                                        context,
                                    )
                                }
                            }
                    )
                }
                ClothingRow(
                    clothing = Clothing.entries,
                    activeClothing = viewModel.activeClothing.value,
                    modifier = Modifier.padding(top = 4.dp)
                )
                Row(modifier = Modifier.weight(1F)) {
                    Column(
                        modifier = Modifier
                            .fillMaxHeight()
                            .weight(0.67F)
                            .padding(top = 4.dp, bottom = 4.dp)
                    ) {
                        viewModel.hourlyGroups.firstOrNull()?.period?.let {
                            CurrentForecastInfoBox(
                                period = it,
                                clothingChanges = viewModel.clothingChanges,
                                accessoryChanges = viewModel.accessoryChanges,
                                bottomOffset = dailyRowHeight,
                                modifier = Modifier.weight(1F)
                            )
                        }
                    }
                    LazyColumn(modifier = Modifier
                        .fillMaxSize()
                        .weight(0.33F)
                    ) {
                        item {
                            HourlyForecastDivider(
                                text = "PREPARED",
                                modifier = Modifier.padding(top = 4.dp)
                            )
                        }
                        itemsIndexed(viewModel.plannedHourlyGroups.value) { index, group ->
                            HourlyForecast(periodGroup = group, isFirst = index == 0)
                        }
                        item {
                            HourlyForecastDivider(text = "LATER")
                        }
                        itemsIndexed(viewModel.laterHourlyGroups.value) { index, group ->
                            HourlyForecast(
                                periodGroup = group,
                                modifier =
                                if (index == viewModel.laterHourlyGroups.value.lastIndex) {
                                    Modifier.padding(bottom = 4.dp)
                                } else Modifier
                            )
                        }
                        item {
                            DailyRowSpacer(dailyRowHeight)
                        }
                    }
                }
            }
            DailyForecastRow(
                dailyGroups = viewModel.dailyGroups,
                modifier = Modifier.align(Alignment.BottomCenter)
            ) { dailyRowHeight = it }
            if (showSettings) {
                SettingsMenu(
                    viewModel = viewModel,
                    modifier = Modifier.align(Alignment.TopEnd)
                ) { shouldRefresh ->
                    if (shouldRefresh) {
                        currentLocation?.let {
                            viewModel.refresh(
                                it.latitude,
                                it.longitude,
                                context
                            )
                        }
                    }
                    showSettings = false
                }
            }
        }
    }
}

@Composable
fun DailyRowSpacer(height: Dp) {
    // Account for daily row, which overlaps this Composable so it can
    // scroll underneath.
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(height)
            .background(Color.Transparent)
    )
}