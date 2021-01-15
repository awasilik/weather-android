package com.elke.weather.util

import com.elke.weather.domain.model.Location
import javax.inject.Inject

class LocationStorageImpl @Inject constructor(
    private val preferences: Preferences
): LocationStorage {

    private val locationKey = "current_location"
    private val defaultLocation = Location.Warsaw

    override fun save(location: Location) {
        preferences.putString(locationKey, location.name)
    }

    override fun retrieve(): Location {
        val locationName = preferences.getString(locationKey)

        return if (locationName.isNullOrEmpty()) {
            defaultLocation
        } else {
            Location.valueOf(locationName)
        }
    }
}