package com.example.weather.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather.domain.WeatherDataHolder
import com.example.weather.domain.model.Location
import com.example.weather.util.LocationStorage
import kotlinx.coroutines.launch

class HostViewModel @ViewModelInject constructor(
    private val dataHolder: WeatherDataHolder,
    private val locationStorage: LocationStorage): ViewModel() {

    val retrievingData = MutableLiveData<Boolean>()
    val errorMessage = MutableLiveData<String?>()
    val locationList = Location.values()

    var currentLocation  = locationStorage.retrieve()
        set(value){
            field = value
            locationStorage.save(value)
            refreshData()
        }

    fun refreshData() {
        viewModelScope.launch {
            retrievingData.value = true
            dataHolder.refreshData(currentLocation) { errorMessage.value = it }
            errorMessage.value = null
            retrievingData.value = false
        }
    }
}

sealed class HostViewState{
    class Error(error: Throwable) : HostViewState()
    class Loading : HostViewState()
    class Content() : HostViewState()
}