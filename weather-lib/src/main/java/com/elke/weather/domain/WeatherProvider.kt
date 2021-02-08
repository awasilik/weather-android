package com.elke.weather.domain

import kotlinx.coroutines.flow.Flow

interface WeatherProvider {
    fun fetchData(): Flow<WeatherDataState>
}