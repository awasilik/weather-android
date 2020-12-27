package com.example.weather.domain

import com.example.weather.domain.model.Location
import com.example.weather.domain.model.WeatherData
import com.example.weather.repository.WeatherRepository
import com.example.weather.repository.model.Weather
import com.example.weather.repository.response.ApiResponse
import java.time.Instant
import java.time.ZoneId
import kotlin.math.roundToInt

class WeatherDataProcessor(
    private val weatherRepository: WeatherRepository = WeatherRepository()
) {
    private val successStatusRange = 200..300

    fun getCurrentWeather(location: Location): ResultWrapper<WeatherData> {
        val apiResponse = weatherRepository.getWeather(location)

        return if (successStatusRange.contains(apiResponse.statusCode)) {
            ResultWrapper.Success(getWeatherData(apiResponse.data))
        }
        else {
            ResultWrapper.Failure(Throwable(apiResponse.statusMessage))
        }
    }

    fun getForecast(location: Location, forecastSpan: Int): ResultWrapper<List<WeatherData>> {
        val apiResponse = weatherRepository.getForecast(location, forecastSpan)

        return if (successStatusRange.contains(apiResponse.statusCode)) {
            ResultWrapper.Success(apiResponse.data.weatherValues!!.map { getWeatherData(it!!) })
        }
        else {
            ResultWrapper.Failure(Throwable(apiResponse.statusMessage))
        }
    }

    private fun getWeatherData(data: Weather) =
        WeatherData(
            parseTime(data.time!!),
            data.weatherValues?.temperature!!.roundToInt(),
            data.wind?.speed!!,
            data.weatherValues.pressure!!,
            data.weatherValues.humidity!!,
            parseUrl(data.description?.get(0)?.icon!!)
        )

    private fun parseUrl(imageId: String) =
        "https://openweathermap.org/img/wn/${imageId}@2x.png"

    private fun parseTime(timeStamp: Long) =
        Instant.ofEpochSecond(timeStamp).atZone(ZoneId.systemDefault()).toLocalTime()
}