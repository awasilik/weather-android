package com.elke.weather.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elke.weather.domain.WeatherDataHolder
import com.elke.weather.domain.model.Location
import com.elke.weather.util.LocationStorage
import kotlinx.coroutines.launch

class HostViewModel @ViewModelInject constructor(
    private val dataHolder: WeatherDataHolder,
    private val locationStorage: LocationStorage): ViewModel() {

    val locationList = Location.values()

    var currentLocation  = locationStorage.retrieve()
        set(value){
            field = value
            locationStorage.save(value)
            refreshData()
        }

    val retrievingData = MutableLiveData<Boolean>()

    val errorMessage = MutableLiveData<String?>()

    fun refreshData() {
        viewModelScope.launch {
            retrievingData.value = true
            dataHolder.refreshData(currentLocation) { errorMessage.value = it }
            errorMessage.value = null
            retrievingData.value = false
        }
    }
}