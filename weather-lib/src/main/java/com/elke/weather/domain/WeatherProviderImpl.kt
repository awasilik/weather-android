package com.elke.weather.domain

import com.elke.weather.domain.dataProcessors.WeatherDataProcessor
import com.elke.weather.domain.location.LocationStorage
import com.elke.weather.repository.api.WeatherRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class WeatherProviderImpl @Inject constructor(
    private val weatherRepository: WeatherRepository,
    private val locationStorage: LocationStorage,
    private val dataProcessor: WeatherDataProcessor
) : WeatherProvider {
    private val successStatusRange = 200..300

    override fun fetchData() = flow {
        emit(WeatherDataState.Loading())

        val location = locationStorage.retrieve()
        val apiResponse = weatherRepository.getData(location)

        if (successStatusRange.contains(apiResponse.statusCode)) {
            val processedData = dataProcessor.process(apiResponse.data)
            emit(WeatherDataState.Success(processedData))
        } else {
            emit(WeatherDataState.Failure(apiResponse.statusMessage))
        }
    }.flowOn(Dispatchers.Default)
}
