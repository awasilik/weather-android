package com.example.weather.repository.model

import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    @field:SerializedName("dt")
    val time: Long? = null,

    @field:SerializedName("weather")
    val description: List<DescriptionValues?>? = null,

    @field:SerializedName("main")
    val weatherValues: WeatherValues? = null,

    @field:SerializedName("wind")
    val wind: WindValues? = null
)