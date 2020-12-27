package com.example.weather.repository.request

import com.example.weather.domain.model.Location
import okhttp3.HttpUrl
import okhttp3.Request

class RequestFactory {
    private val scheme = "https"
    private val host = "api.openweathermap.org"
    private val apiKeyParamName = "appid"
    private val apiKeyParamValue = "e68070b14288ef240ba8b785ada3bf40"
    private val unitsParamName = "units"
    private val unitsParamValue = "metric"
    private val locationParamName = "q"
    private val countParamName = "cnt"

    fun create(requestType: RequestType, location : Location) =
        Request.Builder()
            .url(createUrl(requestType, location))
            .build()

    private fun createUrl(requestType: RequestType, location : Location): HttpUrl {
        val urlBuilder = HttpUrl.Builder()
            .scheme(scheme)
            .host(host)
            .addPathSegment("data")
            .addPathSegment("2.5")
            .addPathSegment(requestType.mode)
            .addQueryParameter(locationParamName, location.city)
            .addQueryParameter(unitsParamName, unitsParamValue)
            .addQueryParameter(apiKeyParamName, apiKeyParamValue)

        if (requestType is RequestType.Forecast)
            urlBuilder.addQueryParameter(countParamName, requestType.amount.toString())

        return urlBuilder.build()
    }
}

