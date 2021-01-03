package com.example.weather.domain

sealed class ResultWrapper<T> {
    class Success<T>(val data: T): ResultWrapper<T>()
    class Failure<T>(val throwable: Throwable) : ResultWrapper<T>()

    val value: T
        get() = when(this) {
            is Success -> data
            is Failure -> throw throwable
        }
}