package com.example.weather.domain.model

import java.time.LocalTime

data class CurrentWeather(
    val time: LocalTime,
    val sunrise: LocalTime,
    val sunset: LocalTime,
    val temperature: Int,
    val feelsLike: Int,
    val windSpeed: Double,
    val pressure: Int,
    val humidity: Int,
    val cloudiness: Int,
    val imageUrl: String,
)
