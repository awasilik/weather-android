package com.example.weather.repository.request

import com.example.weather.domain.model.Location
import okhttp3.HttpUrl
import okhttp3.Request

class RequestCreator {
    private val scheme = "https"
    private val host = "api.openweathermap.org"
    private val apiKeyParamValue = "e68070b14288ef240ba8b785ada3bf40"
    private val unitsValue = "metric"

    fun withLocation(location : Location) =
        Request.Builder()
            .url(createUrl(location))
            .build()

    private fun createUrl(location : Location): HttpUrl {
        val urlBuilder = HttpUrl.Builder()
            .scheme(scheme)
            .host(host)
            .addPathSegment("data")
            .addPathSegment("2.5")
            .addPathSegment("onecall")
            .addQueryParameter("lat", location.latitude.toString())
            .addQueryParameter("lon", location.longitude.toString())
            .addQueryParameter("exclude", "minutely,alerts")
            .addQueryParameter("units", unitsValue)
            .addQueryParameter("appid", apiKeyParamValue)

        return urlBuilder.build()
    }
}

