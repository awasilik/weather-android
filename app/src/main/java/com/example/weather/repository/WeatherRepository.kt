package com.example.weather.repository

import com.example.weather.domain.model.Location
import com.example.weather.repository.model.WeatherApiData
import com.example.weather.repository.request.RequestCreator
import com.example.weather.repository.response.ApiResponse
import com.google.gson.Gson
import okhttp3.OkHttpClient

class WeatherRepository(
    private val httpClient: OkHttpClient = OkHttpClient(),
    private val requestCreator: RequestCreator = RequestCreator(),
    private val gson: Gson = Gson()
) {

    fun getWeatherData(location: Location) : ApiResponse {
        val request = requestCreator.withLocation(location)
        val response = httpClient.newCall(request).execute()
        val data = gson.fromJson(response.body?.string(), WeatherApiData::class.java)

        return ApiResponse(response.code, response.message, data)
    }
}