package com.elke.weather.domain.model

data class WeatherData(
    val currentWeather: CurrentWeather,
    val hourlyWeather: List<HourlyForecast>,
    val dailyWeather: List<DailyForecast>
)
