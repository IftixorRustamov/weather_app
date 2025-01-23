package com.example.weather_app.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.weather_app.R

val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
)

//val Overpass = FontFamily(
//    Font(R.font.overpass, FontWeight.Black, FontStyle.Normal),
//
//    Font(R.font.overpass, FontWeight.Bold, FontStyle.Normal),
//
//    Font(R.font.overpass, FontWeight.Medium, FontStyle.Normal),
//)