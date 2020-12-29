package com.example.weather.domain.model

import java.time.LocalTime

data class HourlyWeather(
    val time: LocalTime,
    val temperature: Int,
    val feelsLike: Int,
    val windSpeed: Double,
    val pressure: Int,
    val humidity: Int,
    val cloudiness: Int,
    val rainChance: Int,
    val imageUrl: String,
)
