package com.elke.weather.ui.viewmodel

import com.elke.weather.domain.WeatherDataState
import com.elke.weather.domain.WeatherProvider
import com.elke.weather.domain.location.LocationStorage
import com.elke.weather.domain.model.CurrentWeather
import com.elke.weather.domain.model.WeatherData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlinx.coroutines.flow.collect

class AppWidgetViewModel @Inject constructor(
    private val weatherProvider: WeatherProvider,
    locationStorage: LocationStorage
) {
    val location = locationStorage.retrieve()

    suspend fun getCurrentWeather() = withContext(Dispatchers.Default) {
        var outValue: CurrentWeather? = null

        weatherProvider.fetchData().collect {
            if (it is WeatherDataState.Success)
                outValue = it.data.currentWeather
        }

        outValue
    }
}