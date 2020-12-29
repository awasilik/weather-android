package com.example.weather.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather.domain.WeatherDataHolder
import com.example.weather.domain.model.Location
import kotlinx.coroutines.launch

class HostViewModel(private val dataHolder: WeatherDataHolder = WeatherDataHolder.instance) : ViewModel() {

    private val defaultLocation = Location.Tarnobrzeg

    val locationList = Location.values()

    val currentLocation = MutableLiveData<Location>().apply { value = defaultLocation }

    val retrievingData = MutableLiveData<Boolean>()

    val errorMessage = MutableLiveData<String?>()

    fun refreshData() {
        viewModelScope.launch {
            retrievingData.value = true
            dataHolder.refreshData(currentLocation.value!!) { errorMessage.value = it }
            errorMessage.value = null
            retrievingData.value = false
        }
    }
}