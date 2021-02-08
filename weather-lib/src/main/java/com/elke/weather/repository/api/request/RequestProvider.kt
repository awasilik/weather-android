package com.elke.weather.repository.api.request

import com.elke.weather.domain.location.Location
import okhttp3.Request

internal interface RequestProvider {
    fun getRequest(location: Location): Request
}