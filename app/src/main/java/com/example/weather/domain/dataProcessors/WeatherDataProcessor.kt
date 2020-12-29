package com.example.weather.domain.dataProcessors

import com.example.weather.domain.model.*
import com.example.weather.repository.WeatherRepository
import com.example.weather.repository.model.Current
import com.example.weather.repository.model.Daily
import com.example.weather.repository.model.Hourly
import com.example.weather.repository.model.WeatherApiData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.Instant
import java.time.ZoneId
import kotlin.math.roundToInt

class WeatherDataProcessor(
    private val weatherRepository: WeatherRepository = WeatherRepository()
) {
    private val successStatusRange = 200..300

    suspend fun fetchData(location: Location): ResultWrapper<WeatherData> {
        val apiResponse = withContext(Dispatchers.Default) { weatherRepository.getWeatherData(location) }

        return if (successStatusRange.contains(apiResponse.statusCode))
            ResultWrapper.Success(getWeatherData(apiResponse.data))
        else
            ResultWrapper.Failure(Throwable(apiResponse.statusMessage))
    }

    private fun getWeatherData(apiData: WeatherApiData) =
        WeatherData(
            getCurrentWeatherData(apiData.current!!),
            apiData.hourly!!.map { getHourlyWeatherData(it) },
            apiData.daily!!.map { getDailyWeatherData(it) }
        )

    private fun getCurrentWeatherData(apiData: Current) =
        CurrentWeather(
            parseTime(apiData.time),
            parseTime(apiData.sunrise),
            parseTime(apiData.sunset),
            parseTemperature(apiData.temperature),
            parseTemperature(apiData.temperature),
            apiData.windSpeed!!,
            apiData.pressure!!,
            apiData.humidity!!,
            apiData.cloudiness!!,
            parseImageUrl(apiData.description?.get(0)?.icon)
        )

    private fun getHourlyWeatherData(apiData: Hourly) =
        HourlyWeather(
            parseTime(apiData.time),
            parseTemperature(apiData.temperature),
            parseTemperature(apiData.temperature),
            apiData.windSpeed!!,
            apiData.pressure!!,
            apiData.humidity!!,
            apiData.cloudiness!!,
            parseRainChancePercent(apiData.rainChance),
            parseImageUrl(apiData.description?.get(0)?.icon)
        )

    private fun getDailyWeatherData(apiData: Daily) =
        DailyWeather(
            parseTime(apiData.time),
            parseTemperature(apiData.forecastTemperature?.day),
            parseTemperature(apiData.forecastTemperature?.night),
            parseTemperature(apiData.forecastFeelsLike?.day),
            parseTemperature(apiData.forecastFeelsLike?.night),
            apiData.windSpeed!!,
            apiData.pressure!!,
            apiData.humidity!!,
            apiData.cloudiness!!,
            parseRainChancePercent(apiData.rainChance),
            parseImageUrl(apiData.description?.get(0)?.icon)
        )

    private fun parseRainChancePercent(rainChanceFraction: Double?) =
        (rainChanceFraction!!.times(100)).roundToInt()

    private fun parseTemperature(temperature: Double?) =
        temperature!!.roundToInt()

    private fun parseImageUrl(imageId: String?) =
        "https://openweathermap.org/img/wn/${imageId}@2x.png"

    private fun parseTime(timeStamp: Long?) =
        Instant.ofEpochSecond(timeStamp!!).atZone(ZoneId.systemDefault()).toLocalTime()
}