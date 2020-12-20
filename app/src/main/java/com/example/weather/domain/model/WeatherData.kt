package com.example.weather.domain.model

import java.time.LocalTime

data class WeatherData(
    val time: LocalTime,
    val temperature: Double,
    val windSpeed: Double,
    val pressure: Int,
    val humidity: Int,
    val imageUrl: String
)