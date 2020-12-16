package com.example.weather.repository.model

import com.google.gson.annotations.SerializedName

data class WeatherValues(
    @field:SerializedName("temp")
    val temperature: Double? = null,

    @field:SerializedName("humidity")
    val humidity: Int? = null,

    @field:SerializedName("pressure")
    val pressure: Int? = null
)