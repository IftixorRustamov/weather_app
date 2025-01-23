package com.example.weather_app.presentation.widgets

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weather_app.data.remote.models.WeatherModel
import com.example.weather_app.presentation.viewModels.getWeatherIcon

@Composable
fun WeatherResponseOnSuccess(data: WeatherModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 25.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween,
    ) {
        // Location
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.Bottom,
        ) {
            Icon(
                imageVector = Icons.Default.LocationOn,
                contentDescription = "Location",
                modifier = Modifier.size(40.dp),
                tint = Color(0xff8d021f)
            )
            Text(text = data.location.name, fontSize = 30.sp, color = Color.White)
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = data.location.country, fontSize = 15.sp, color = Color.White.copy(5f))
        }

        val iconResId = getWeatherIcon(data.current.condition.text)
        Icon(
            painter = painterResource(id = iconResId),
            contentDescription = data.current.condition.text,
            modifier = Modifier
                .size(258.dp)
                .padding(8.dp),
            tint = Color.Unspecified
        )

        Text(
            text = data.current.condition.text,
            fontSize = 30.sp,
            color = Color.White,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Center,
            maxLines = 2,
        )


        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.8f))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = " ${data.current.temp_c}°",
                    fontSize = 76.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                )

                Spacer(modifier = Modifier.height(10.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    WeatherInfo(
                        key = "Local Time",
                        value = data.location.localtime.split(" ")[1]
                    )
                    WeatherInfo(
                        key = "Latitude",
                        value = data.location.lat
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    WeatherInfo(key = "Humidity", value = data.current.humidity)
                    WeatherInfo(key = "Wind Speed", value = data.current.wind_kph + " km/h")
                }
            }
        }
    }
}


