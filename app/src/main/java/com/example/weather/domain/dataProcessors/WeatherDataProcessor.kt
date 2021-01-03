package com.example.weather.domain.dataProcessors

import com.example.weather.domain.model.CurrentWeather
import com.example.weather.domain.model.DailyWeather
import com.example.weather.domain.model.HourlyWeather
import com.example.weather.domain.model.WeatherData
import com.example.weather.repository.model.Current
import com.example.weather.repository.model.Daily
import com.example.weather.repository.model.Hourly
import com.example.weather.repository.model.WeatherApiData
import java.time.Instant
import java.time.ZoneId
import javax.inject.Inject
import kotlin.math.roundToInt

class WeatherDataProcessor @Inject constructor() {

    fun process(data: WeatherApiData) = WeatherData(
        getCurrentWeatherData(data.current!!),
        data.hourly!!.map { getHourlyWeatherData(it) },
        data.daily!!.map { getDailyWeatherData(it) }
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
        Instant.ofEpochSecond(timeStamp!!).atZone(ZoneId.systemDefault()).toLocalDateTime()
}