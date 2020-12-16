package com.example.weather.repository

import okhttp3.HttpUrl
import okhttp3.Request

class RequestFactory {
    private val headerKeyName = "x-rapidapi-key"
    private val headerHostName = "x-rapidapi-host"

    private val scheme = "https"
    private val host = "community-open-weather-map.p.rapidapi.com"
    private val apiKey = "ef2c064eddmshbeb0530f6ccafc8p1a7bf5jsn4944ecebea01"
    private val locationQueryParamName = "q"
    private val countQueryParamName = "cnt"

    fun create(requestType: RequestType) =
        Request.Builder()
            .url(createUrl(requestType))
            .get()
            .addHeader(headerKeyName, apiKey)
            .addHeader(headerHostName, host)
            .build()

    private fun createUrl(requestType: RequestType): HttpUrl {
        val urlBuilder = HttpUrl.Builder()
            .scheme(scheme)
            .host(host)
            .addPathSegment(requestType.mode)
            .addQueryParameter(locationQueryParamName, requestType.locationValue)

        if (requestType is RequestType.Forecast)
            urlBuilder.addQueryParameter(countQueryParamName, requestType.amount.toString())

        return urlBuilder.build()
    }
}

