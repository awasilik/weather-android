package com.example.weather.domain.dataProcessors

import com.example.weather.domain.model.Weather
import com.example.weather.api.model.ApiCurrentWeather
import com.example.weather.api.model.ApiDaily
import com.example.weather.api.model.ApiHourlyForecast
import com.example.weather.api.model.ApiWeather
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