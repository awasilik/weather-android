package com.example.weather.repository.model

import com.google.gson.annotations.SerializedName

data class Current(
    @field:SerializedName("dt")
    val time: Long? = null,

    @field:SerializedName("sunrise")
    val sunrise: Long? = null,

    @field:SerializedName("sunset")
    val sunset: Long? = null,

    @field:SerializedName("temp")
    val temperature: Double? = null,

    @field:SerializedName("feels_like")
    val feelsLike: Double? = null,

    @field:SerializedName("pressure")
    val pressure: Int? = null,

    @field:SerializedName("humidity")
    val humidity: Int? = null,

    @field:SerializedName("clouds")
    val cloudiness: Int? = null,

    @field:SerializedName("wind_speed")
    val windSpeed: Double? = null,

    @field:SerializedName("weather")
    val description: List<Description>? = null
)