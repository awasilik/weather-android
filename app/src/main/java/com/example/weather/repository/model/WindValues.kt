package com.example.weather.repository.model

import com.google.gson.annotations.SerializedName

data class WindValues(

    @field:SerializedName("speed")
    val speed: Double? = null
)
