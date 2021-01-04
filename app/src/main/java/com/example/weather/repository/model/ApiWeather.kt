package com.example.weather.repository.model

import com.google.gson.annotations.SerializedName

data class ApiWeather(
    @field:SerializedName("current")
    val current: ApiCurrentWeather? = null,

    @field:SerializedName("hourly")
    val hourly: List<ApiHourlyForecast>? = null,

    @field:SerializedName("daily")
    val daily: List<ApiDaily>? = null
)



