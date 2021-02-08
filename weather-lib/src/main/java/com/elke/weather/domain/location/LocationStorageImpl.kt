package com.elke.weather.domain.location

import com.elke.weather.util.Preferences
import javax.inject.Inject

internal class LocationStorageImpl @Inject constructor(
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