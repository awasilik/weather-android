package com.elke.weather.api.model

import com.google.gson.annotations.SerializedName

data class Description(
    @field:SerializedName("main")
    val name: String? = null,

    @field:SerializedName("icon")
    val icon: String? = null
)