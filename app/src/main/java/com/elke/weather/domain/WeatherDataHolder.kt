package com.elke.weather.domain

import androidx.lifecycle.MutableLiveData
import com.elke.weather.domain.dataProcessors.WeatherDataProcessor
import com.elke.weather.domain.model.*
import com.elke.weather.api.WeatherRepository
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

    val hourlyForecast = MutableLiveData<List<HourlyForecast>>()

    val dailyForecast = MutableLiveData<List<DailyForecast>>()

    suspend fun refreshData(location: Location, errorHandler: (String?) -> Unit) =
        withContext(Dispatchers.Main) {
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