package com.example.weather.domain.model

import com.example.weather.domain.Location
import com.example.weather.repository.WeatherRepository
import com.example.weather.repository.model.WeatherResponse
import java.time.Instant
import java.time.ZoneId
import kotlin.math.roundToInt

class WeatherDataProcessor(
    private val weatherRepository: WeatherRepository = WeatherRepository()
) {
    fun getCurrentWeather(location: Location): WeatherData {
        val response = weatherRepository.getWeather(location)
        return getWeatherData(response)
    }

    fun getForecast(location: Location, forecastSpan: Int): List<WeatherData> {
        val response = weatherRepository.getForecast(location, forecastSpan)
        return response.weatherValues!!.map { getWeatherData(it!!) }
    }

    private fun getWeatherData(response: WeatherResponse) =
        WeatherData(
            parseTime(response.time!!),
            response.weatherValues?.temperature!!.roundToInt(),
            response.wind?.speed!!,
            response.weatherValues.pressure!!,
            response.weatherValues.humidity!!,
            parseUrl(response.description?.get(0)?.icon!!)
        )

    private fun parseUrl(imageId: String) =
        "https://openweathermap.org/img/wn/${imageId}@2x.png"

    private fun parseTime(timeStamp: Long) =
        Instant.ofEpochSecond(timeStamp).atZone(ZoneId.systemDefault()).toLocalTime()
}