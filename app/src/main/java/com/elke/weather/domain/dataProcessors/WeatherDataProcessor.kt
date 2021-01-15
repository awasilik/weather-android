package com.elke.weather.domain.dataProcessors

import com.elke.weather.domain.model.Weather
import com.elke.weather.api.model.ApiCurrentWeather
import com.elke.weather.api.model.ApiDaily
import com.elke.weather.api.model.ApiHourlyForecast
import com.elke.weather.api.model.ApiWeather
import javax.inject.Inject

class WeatherDataProcessor @Inject constructor() : DataProcessor<ApiWeather, Weather>() {

    override fun process(apiData: ApiWeather) = Weather(
        getCurrentWeatherData(apiData.current!!),
        apiData.hourly!!.map { getHourlyWeatherData(it) },
        apiData.daily!!.map { getDailyWeatherData(it) }
    )

    private fun getCurrentWeatherData(apiData: ApiCurrentWeather) =
        CurrentWeatherDataProcessor().process(apiData)

    private fun getHourlyWeatherData(apiData: ApiHourlyForecast) =
        HourlyForecastDataProcessor().process(apiData)

    private fun getDailyWeatherData(apiData: ApiDaily) =
        DailyForecastDataProcessor().process(apiData)
}