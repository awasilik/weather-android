package com.elke.weather.api

import com.elke.weather.domain.model.Location
import com.elke.weather.api.model.ApiWeather
import com.elke.weather.api.request.RequestProvider
import com.elke.weather.api.response.ApiResponse
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
        val data = gson.fromJson(response.body?.string(), ApiWeather::class.java)

        return ApiResponse(response.code, response.message, data)
    }
}