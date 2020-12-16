package com.example.weather.repository.model

import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    @field:SerializedName("dt")
    val time: Int? = null,

    @field:SerializedName("weather")
    val description: List<WeatherDescription?>? = null,

    @field:SerializedName("main")
    val weatherValues: WeatherValues? = null,

    @field:SerializedName("wind")
    val wind: WindValues? = null
)