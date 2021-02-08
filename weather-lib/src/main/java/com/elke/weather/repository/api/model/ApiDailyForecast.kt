package com.elke.weather.repository.api.model

import com.google.gson.annotations.SerializedName

internal data class ApiDaily(
    @SerializedName("dt")
    val time: Long? = null,

    @SerializedName("temp")
    val forecastTemperature: ForecastTemp? = null,

    @SerializedName("feels_like")
    val forecastFeelsLike: ForecastTemp? = null,

    @SerializedName("pressure")
    val pressure: Int? = null,

    @SerializedName("humidity")
    val humidity: Int? = null,

    @SerializedName("wind_speed")
    val windSpeed: Double? = null,

    @SerializedName("clouds")
    val cloudiness: Int? = null,

    @SerializedName("pop")
    val rainChance: Double? = null,

    @SerializedName("weather")
    val description: List<Description>? = null
)

internal data class ForecastTemp(
    @SerializedName("day")
    val day: Double? = null,

    @SerializedName("night")
    val night: Double? = null,
)