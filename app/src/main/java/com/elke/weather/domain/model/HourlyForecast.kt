package com.elke.weather.domain.model

import java.time.LocalDateTime

data class HourlyForecast(
    val time: LocalDateTime,
    val temperature: Int,
    val feelsLike: Int,
    val windSpeed: Double,
    val pressure: Int,
    val humidity: Int,
    val cloudiness: Int,
    val rainChance: Int,
    val imageUrl: String,
)
