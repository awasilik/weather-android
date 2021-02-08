package com.elke.weather.di

import com.elke.weather.domain.WeatherProvider
import com.elke.weather.domain.WeatherProviderImpl
import com.elke.weather.domain.location.LocationStorage
import com.elke.weather.domain.location.LocationStorageImpl
import com.elke.weather.repository.api.WeatherRepository
import com.elke.weather.repository.api.WeatherRepositoryImpl
import com.elke.weather.repository.api.request.RequestProvider
import com.elke.weather.repository.api.request.RequestProviderImpl
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal class WeatherModule {

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

    @Provides
    @Singleton
    fun provideDataHolder(dataHolderImpl: WeatherProviderImpl): WeatherProvider =
        dataHolderImpl
}