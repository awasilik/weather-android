package com.example.weather.repository.api.model

import com.google.gson.annotations.SerializedName

data class ApiCurrentWeather(
    @SerializedName("dt")
    val time: Long? = null,
    @SerializedName("sunrise")
    val sunrise: Long? = null,
    @SerializedName("sunset")
    val sunset: Long? = null,
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
    val description: List<Description>? = null
)