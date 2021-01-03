package com.example.weather.module

import com.example.weather.repository.WeatherRepository
import com.example.weather.repository.WeatherRepositoryImpl
import com.example.weather.repository.request.RequestProvider
import com.example.weather.repository.request.RequestProviderImpl
import com.example.weather.util.LocationStorage
import com.example.weather.util.LocationStorageImpl
import com.google.gson.Gson
import dagger.Binds
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