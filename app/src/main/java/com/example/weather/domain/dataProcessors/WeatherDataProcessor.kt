package com.example.weather.domain.dataProcessors

import com.example.weather.domain.model.Weather
import com.example.weather.repository.model.ApiCurrentWeather
import com.example.weather.repository.model.ApiDaily
import com.example.weather.repository.model.ApiHourlyForecast
import com.example.weather.repository.model.ApiWeather
import javax.inject.Inject

class WeatherDataProcessor @Inject constructor() : DataProcessor<ApiWeather, Weather>() {

    override fun process(data: ApiWeather) = Weather(
        getCurrentWeatherData(data.current!!),
        data.hourly!!.map { getHourlyWeatherData(it) },
        data.daily!!.map { getDailyWeatherData(it) }
    )

    private fun getCurrentWeatherData(apiData: ApiCurrentWeather) =
        CurrentWeatherDataProcessor().process(apiData)

    private fun getHourlyWeatherData(apiData: ApiHourlyForecast) =
        HourlyForecastDataProcessor().process(apiData)

    private fun getDailyWeatherData(apiData: ApiDaily) =
        DailyForecastDataProcessor().process(apiData)
}