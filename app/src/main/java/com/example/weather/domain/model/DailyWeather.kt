package com.example.weather.domain.model

import java.time.LocalTime

data class DailyWeather(
    val time: LocalTime,
    val dayTemperature: Int,
    val nightTemperature: Int,
    val dayFeelsLike: Int,
    val nightFeelsLike: Int,
    val windSpeed: Double,
    val pressure: Int,
    val humidity: Int,
    val cloudiness: Int,
    val rainChance: Int,
    val imageUrl: String,
)
