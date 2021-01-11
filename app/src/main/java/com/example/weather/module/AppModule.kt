package com.example.weather.module

import com.example.weather.repository.api.WeatherRepository
import com.example.weather.repository.api.WeatherRepositoryImpl
import com.example.weather.repository.api.request.RequestProvider
import com.example.weather.repository.api.request.RequestProviderImpl
import com.example.weather.util.LocationStorage
import com.example.weather.util.LocationStorageImpl
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

    // try Koin
    @Provides
    @Singleton
    fun provideOkHttpClient() = OkHttpClient()

    @Provides
    fun provideGson() = Gson()

    @Provides
    fun provideRequestProvider(requestProviderImpl: RequestProviderImpl): RequestProvider =
        requestProviderImpl

    @Provides
    fun provideWeatherRepository(repositoryImpl: WeatherRepositoryImpl): WeatherRepository =
        repositoryImpl

    @Provides
    fun provideLocationStorage(locationStorageImpl: LocationStorageImpl): LocationStorage =
        locationStorageImpl
}