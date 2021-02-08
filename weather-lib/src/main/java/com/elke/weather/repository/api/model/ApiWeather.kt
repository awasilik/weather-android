package com.elke.weather.repository.api.model

import com.google.gson.annotations.SerializedName

internal data class ApiWeather(
    @SerializedName("current")
    val current: ApiCurrentWeather? = null,

    @SerializedName("hourly")
    val hourly: List<ApiHourlyForecast>? = null,

    @SerializedName("daily")
    val daily: List<ApiDaily>? = null
)



