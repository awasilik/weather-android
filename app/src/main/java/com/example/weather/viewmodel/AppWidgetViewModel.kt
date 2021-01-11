package com.example.weather.viewmodel

import com.example.weather.domain.dataProcessors.CurrentWeatherDataProcessor
import com.example.weather.repository.api.WeatherRepository
import com.example.weather.util.LocationStorage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AppWidgetViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository,
    locationStorage: LocationStorage)  {

    private val successStatusRange = 200..300

    val location = locationStorage.retrieve()

    suspend fun getCurrentWeather() = withContext(Dispatchers.Default) {
        val apiResponse = weatherRepository.getData(location)

        if (successStatusRange.contains(apiResponse.statusCode))
            CurrentWeatherDataProcessor().process(apiResponse.data.current!!)
        else
            throw Throwable("Could not retrieve data")
    }
}