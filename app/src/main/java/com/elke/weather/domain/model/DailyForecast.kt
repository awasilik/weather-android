package com.elke.weather.domain.model

import java.time.LocalDateTime

data class DailyForecast(
    val time: LocalDateTime,
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
