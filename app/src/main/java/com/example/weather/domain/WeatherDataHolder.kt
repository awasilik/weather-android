package com.example.weather.domain

import androidx.lifecycle.MutableLiveData
import com.example.weather.domain.dataProcessors.ResultWrapper
import com.example.weather.domain.dataProcessors.WeatherDataProcessor
import com.example.weather.domain.model.*
import com.example.weather.repository.WeatherRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherDataHolder @Inject constructor(
    private val weatherRepository: WeatherRepository,
    private val dataProcessor: WeatherDataProcessor) {

    private val successStatusRange = 200..300

    val currentWeather = MutableLiveData<CurrentWeather>()

    val hourlyForecast = MutableLiveData<List<HourlyWeather>>()

    val dailyForecast = MutableLiveData<List<DailyWeather>>()

    suspend fun refreshData(location: Location, errorHandler: (String?) -> Unit) = withContext(Dispatchers.Main) {
        try {
            val weatherData = fetchData(location).value

            currentWeather.value = weatherData.currentWeather
            hourlyForecast.value = weatherData.hourlyWeather
            dailyForecast.value = weatherData.dailyWeather
        } catch (e: Throwable) {
            errorHandler.invoke(e.message)
        }
    }

    private suspend fun fetchData(location: Location) = withContext(Dispatchers.Default) {
        val apiResponse = weatherRepository.getData(location)

        if (successStatusRange.contains(apiResponse.statusCode))
            ResultWrapper.Success(dataProcessor.process(apiResponse.data))
        else
            ResultWrapper.Failure(Throwable(apiResponse.statusMessage))
    }
}