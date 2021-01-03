package com.example.weather.repository

import com.example.weather.domain.model.Location
import com.example.weather.repository.model.WeatherApiData
import com.example.weather.repository.request.RequestProvider
import com.example.weather.repository.response.ApiResponse
import com.google.gson.Gson
import okhttp3.OkHttpClient
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherRepositoryImpl @Inject constructor(
    private val httpClient: OkHttpClient,
    private val requestProvider: RequestProvider,
    private val gson: Gson,
): WeatherRepository {

    override fun getData(location: Location) : ApiResponse {
        val request = requestProvider.getRequest(location)
        val response = httpClient.newCall(request).execute()
        val data = gson.fromJson(response.body?.string(), WeatherApiData::class.java)

        return ApiResponse(response.code, response.message, data)
    }
}