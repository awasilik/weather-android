package com.example.weather.repository.model

import com.google.gson.annotations.SerializedName

data class Forecast(
    @field:SerializedName("list")
    val weatherValues: List<Weather?>? = null
)
