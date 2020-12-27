package com.example.weather.repository

import com.example.weather.domain.model.Location
import com.example.weather.repository.model.Forecast
import com.example.weather.repository.model.Weather
import com.example.weather.repository.request.RequestFactory
import com.example.weather.repository.request.RequestType
import com.example.weather.repository.response.ApiResponse
import com.google.gson.Gson
import okhttp3.OkHttpClient

class WeatherRepository(
    private val httpClient: OkHttpClient = OkHttpClient(),
    private val requestFactory: RequestFactory = RequestFactory(),
    private val gson: Gson = Gson()
) {

    fun getWeather(location: Location) =
        get<Weather>(RequestType.Weather(), location)

    fun getForecast(location: Location, forecastSpan: Int) =
        get<Forecast>(RequestType.Forecast(forecastSpan), location)

    private inline fun <reified T> get(requestType: RequestType, location: Location): ApiResponse<T> {
        val request = requestFactory.create(requestType, location)
        val response = httpClient.newCall(request).execute()
        val data = gson.fromJson(response.body?.string(), T::class.java)

        return ApiResponse(response.code, response.message, data)
    }
}