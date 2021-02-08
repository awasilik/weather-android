package com.elke.weather.repository.api.model

import com.google.gson.annotations.SerializedName

internal data class ApiHourlyForecast (
    @SerializedName("dt")
    val time: Long? = null,

    @SerializedName("temp")
    val temperature: Double? = null,

    @SerializedName("feels_like")
    val feelsLike: Double? = null,

    @SerializedName("pressure")
    val pressure: Int? = null,

    @SerializedName("humidity")
    val humidity: Int? = null,

    @SerializedName("clouds")
    val cloudiness: Int? = null,

    @SerializedName("wind_speed")
    val windSpeed: Double? = null,

    @SerializedName("weather")
    val description: List<Description>? = null,

    @SerializedName("pop")
    val rainChance: Double? = null
)