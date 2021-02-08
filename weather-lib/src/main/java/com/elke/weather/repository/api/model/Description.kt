package com.elke.weather.repository.api.model

import com.google.gson.annotations.SerializedName

internal data class Description(
    @SerializedName("main")
    val name: String? = null,

    @SerializedName("icon")
    val icon: String? = null
)