package com.example.weather.domain

import androidx.lifecycle.MutableLiveData
import com.example.weather.domain.dataProcessors.WeatherDataProcessor
import com.example.weather.domain.model.CurrentWeather
import com.example.weather.domain.model.DailyWeather
import com.example.weather.domain.model.HourlyWeather
import com.example.weather.domain.model.Location
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class WeatherDataHolder(private val dataProcessor: WeatherDataProcessor = WeatherDataProcessor()) {

    companion object {
        val instance = WeatherDataHolder()
    }

    val currentWeather = MutableLiveData<CurrentWeather>()

    val hourlyForecast = MutableLiveData<List<HourlyWeather>>()

    val dailyForecast = MutableLiveData<List<DailyWeather>>()

    suspend fun refreshData(location: Location, errorHandler: (String?) -> Unit) = withContext(Dispatchers.Main) {
        try {
            val weatherData = dataProcessor.fetchData(location).value

            currentWeather.value = weatherData.currentWeather
            hourlyForecast.value = weatherData.hourlyWeather
            dailyForecast.value = weatherData.dailyWeather
        } catch (e: Throwable) {
            errorHandler.invoke(e.message)
        }
    }
}