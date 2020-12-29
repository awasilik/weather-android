package com.example.weather.repository.model

import com.google.gson.annotations.SerializedName

data class WeatherApiData(
    @field:SerializedName("current")
    val current: Current? = null,

    @field:SerializedName("hourly")
    val hourly: List<Hourly>? = null,

    @field:SerializedName("daily")
    val daily: List<Daily>? = null
)



