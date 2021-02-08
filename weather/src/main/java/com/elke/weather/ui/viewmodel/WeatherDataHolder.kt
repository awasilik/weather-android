package com.elke.weather.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.elke.weather.domain.WeatherDataState
import com.elke.weather.domain.WeatherProvider
import com.elke.weather.domain.model.WeatherData
import javax.inject.Inject
import kotlinx.coroutines.flow.collect
import javax.inject.Singleton

@Singleton
class WeatherDataHolder @Inject constructor(private val weatherProvider: WeatherProvider) {

    private val processingInternal = MutableLiveData(false)
    val processing: LiveData<Boolean> = processingInternal

    private val weatherDataInternal = MutableLiveData<WeatherData>()
    val weatherData: LiveData<WeatherData> = weatherDataInternal

    private val errorMessageInternal = MutableLiveData<String?>(null)
    val errorMessage: LiveData<String?> = errorMessageInternal

    suspend fun refreshData() {
        weatherProvider.fetchData().collect { dataState ->
            when (dataState) {
                is WeatherDataState.Empty -> setEmptyState()
                is WeatherDataState.Loading -> setLoadingState()
                is WeatherDataState.Failure -> setErrorState(dataState.errorMessage)
                is WeatherDataState.Success -> setSuccessState(dataState.data)
                else -> throw IllegalStateException("No such data state")
            }
        }
    }

    private fun setEmptyState(){
        errorMessageInternal.postValue(null)
        processingInternal.postValue(false)
    }

    private fun setLoadingState(){
        errorMessageInternal.postValue(null)
        processingInternal.postValue(true)
    }

    private fun setErrorState(errorMessage: String){
        processingInternal.postValue(false)
        errorMessageInternal.postValue(errorMessage)
    }

    private fun setSuccessState(data: WeatherData){
        processingInternal.postValue(false)
        errorMessageInternal.postValue(null)
        weatherDataInternal.postValue(data)
    }
}