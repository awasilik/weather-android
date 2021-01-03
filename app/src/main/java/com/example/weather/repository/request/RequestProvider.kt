package com.example.weather.repository.request

import com.example.weather.domain.model.Location
import okhttp3.Request

interface RequestProvider {
    fun getRequest(location: Location): Request
}