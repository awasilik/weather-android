package com.elke.weather.api.request

import com.elke.weather.domain.model.Location
import okhttp3.Request

interface RequestProvider {
    fun getRequest(location: Location): Request
}