package com.elke.weather.module

import com.elke.weather.api.WeatherRepository
import com.elke.weather.api.WeatherRepositoryImpl
import com.elke.weather.api.request.RequestProvider
import com.elke.weather.api.request.RequestProviderImpl
import com.elke.weather.util.LocationStorage
import com.elke.weather.util.LocationStorageImpl
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideOkHttpClient() = OkHttpClient()

    @Provides
    fun provideGson() = Gson()

    @Provides
    @Singleton
    fun provideRequestProvider(requestProviderImpl: RequestProviderImpl): RequestProvider =
        requestProviderImpl

    @Provides
    @Singleton
    fun provideWeatherRepository(repositoryImpl: WeatherRepositoryImpl): WeatherRepository =
        repositoryImpl

    @Provides
    @Singleton
    fun provideLocationStorage(locationStorageImpl: LocationStorageImpl): LocationStorage =
        locationStorageImpl
}