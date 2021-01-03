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

class WeatherDataProcessor @Inject constructor() : DataProcessor<WeatherApiData, WeatherData>() {

    override fun process(data: WeatherApiData) = WeatherData(
        getCurrentWeatherData(data.current!!),
        data.hourly!!.map { getHourlyWeatherData(it) },
        data.daily!!.map { getDailyWeatherData(it) }
    )

    private fun getCurrentWeatherData(apiData: Current) =
        CurrentWeatherDataProcessor().process(apiData)

    private fun getHourlyWeatherData(apiData: Hourly) =
        HourlyForecastDataProcessor().process(apiData)

    private fun getDailyWeatherData(apiData: Daily) =
        DailyForecastDataProcessor().process(apiData)
}