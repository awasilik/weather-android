package com.example.weather.repository

import com.example.weather.domain.Location
import com.example.weather.repository.model.ForecastResponse
import com.example.weather.repository.model.WeatherResponse
import com.example.weather.repository.request.RequestFactory
import com.example.weather.repository.request.RequestType
import com.google.gson.Gson
import okhttp3.OkHttpClient
import kotlin.coroutines.CoroutineContext


class WeatherRepository(
    private val httpClient: OkHttpClient = OkHttpClient(),
    private val requestFactory: RequestFactory = RequestFactory(),
    private val gson: Gson = Gson()
) {

    fun getWeather(location: Location) =
        get<WeatherResponse>(RequestType.Weather(), location)

    fun getForecast(location: Location, forecastSpan: Int) =
        get<ForecastResponse>(RequestType.Forecast(forecastSpan), location)

    private inline fun <reified T> get(requestType: RequestType, location: Location): T {

        val request = requestFactory.create(requestType, location)
        val response = httpClient.newCall(request).execute()

        return gson.fromJson(response.body?.string(), T::class.java)
    }
}