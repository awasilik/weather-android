package com.example.weather.repository.model

import com.google.gson.annotations.SerializedName

data class ForecastResponse(
    @field:SerializedName("list")
    val weatherValues: List<WeatherResponse?>? = null
)
