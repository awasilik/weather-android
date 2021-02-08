package com.elke.weather.ui.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.elke.weather.domain.location.Location
import com.elke.weather.domain.location.LocationStorage
import kotlinx.coroutines.launch

class HostViewModel @ViewModelInject constructor(
    private val dataHolder: WeatherDataHolder,
    private val locationStorage: LocationStorage
): ViewModel() {

    val locationList = Location.values()

    var currentLocation
        get() = locationStorage.retrieve()
        set(value) = locationStorage.save(value)

    val processing: LiveData<Boolean> =
        Transformations.map(dataHolder.processing) { it }

    val errorMessage: LiveData<String?> =
        Transformations.map(dataHolder.errorMessage) { it }

    fun refreshData() {
        viewModelScope.launch {
            dataHolder.refreshData()
        }
    }
}