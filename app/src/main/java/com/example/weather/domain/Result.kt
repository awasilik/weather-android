package com.example.weather.domain

sealed class Result<T> {
    class Success<T>(val data: T): Result<T>()
    class Failure<T>(val throwable: Throwable) : Result<T>()

    val value: T
        get() = when(this) {
            is Success -> data
            is Failure -> throw throwable
        }
}