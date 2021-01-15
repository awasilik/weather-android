package com.elke.weather.domain.model

import java.time.LocalDateTime

data class CurrentWeather(
    val time: LocalDateTime,
    val sunrise: LocalDateTime,
    val sunset: LocalDateTime,
    val temperature: Int,
    val feelsLike: Int,
    val windSpeed: Double,
    val pressure: Int,
    val humidity: Int,
    val cloudiness: Int,
    val imageUrl: String,
)
