package com.example.weather_app.presentation.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import com.example.weather_app.presentation.viewModels.WeatherViewModel
import com.example.weather_app.data.remote.NetworkResponse
import com.example.weather_app.presentation.widgets.WeatherResponseOnSuccess
import com.example.weather_app.presentation.widgets.WeatherResponseOnError
import com.example.weather_app.presentation.viewModels.LocationViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Homepage(viewModel: WeatherViewModel, locationViewModel: LocationViewModel) {
    var city by remember { mutableStateOf("") }
    val weatherResult = viewModel.weatherResult.observeAsState()
    val keyboardController = LocalSoftwareKeyboardController.current
    val currentLocation by locationViewModel.location.observeAsState()

    // Fetch weather data based on location when location is available
    LaunchedEffect(currentLocation) {
        currentLocation?.let {
            viewModel.getDataByLocation(it.latitude, it.longitude)
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.linearGradient(
                    colors = listOf(
                        Color(0xFF1E90FF),
                        Color(0xFF87CEFA),
                    ),
                    start = Offset(0f, Float.POSITIVE_INFINITY),
                    end = Offset(Float.POSITIVE_INFINITY, 0f)
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                TextField(
                    modifier = Modifier
                        .width(370.dp)
                        .border(
                            width = 0.dp,
                            brush = Brush.horizontalGradient(
                                listOf(
                                    Color.Transparent,
                                    Color.Transparent
                                )
                            ),
                            shape = RoundedCornerShape(8.dp),
                        )
                        .padding(4.dp)
                        .background(Color.White),
                    label = {
                        Text(text = "Search")
                    },
                    value = city,
                    onValueChange = {
                        city = it
                    },

                    )
                IconButton(onClick = {
                    viewModel.getData(city)
                    keyboardController?.hide()
                }) {
                    Icon(imageVector = Icons.Default.Search, contentDescription = "Search", modifier = Modifier.size(30.dp) )
                }
            }

            when (val result = weatherResult.value) {
                is NetworkResponse.Error -> {
                    WeatherResponseOnError(
                        result = result,
                        onRetryClick = {
                            viewModel.getData(city)
                        }
                    )
                }

                is NetworkResponse.Loading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            CircularProgressIndicator()
                            Spacer(modifier = Modifier.height(16.dp))
                            Text(text = "Loading...")
                        }

                    }
                }

                is NetworkResponse.Success -> {
                    Column {
                        WeatherResponseOnSuccess(data = result.data)
                    }
                }

                null -> {}
            }
        }
    }

}








