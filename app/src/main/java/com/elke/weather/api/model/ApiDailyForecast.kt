package com.elke.weather.api.model

import com.google.gson.annotations.SerializedName

data class ApiDaily(
    @field:SerializedName("dt")
    val time: Long? = null,

    @field:SerializedName("temp")
    val forecastTemperature: ForecastTemp? = null,

    @field:SerializedName("feels_like")
    val forecastFeelsLike: ForecastTemp? = null,

    @field:SerializedName("pressure")
    val pressure: Int? = null,

    @field:SerializedName("humidity")
    val humidity: Int? = null,

    @field:SerializedName("wind_speed")
    val windSpeed: Double? = null,

    @field:SerializedName("clouds")
    val cloudiness: Int? = null,

    @field:SerializedName("pop")
    val rainChance: Double? = null,

    @field:SerializedName("weather")
    val description: List<Description>? = null
)

data class ForecastTemp(
    @field:SerializedName("day")
    val day: Double? = null,

    @field:SerializedName("night")
    val night: Double? = null,
)