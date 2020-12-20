package com.example.weather.repository.model

import com.google.gson.annotations.SerializedName

data class DescriptionValues(

    @field:SerializedName("main")
    val name: String? = null,

    @field:SerializedName("icon")
    val icon: String? = null
)