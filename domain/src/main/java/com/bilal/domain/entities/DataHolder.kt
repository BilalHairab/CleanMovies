package com.bilal.domain.entities;

sealed class DataHolder<out T : Any> {
    data class Success<out T : Any>(val data: T) : DataHolder<T>()

    data class Fail(val error: ResultError) : DataHolder<Nothing>()
}

enum class ResultError {
    DATA_NOT_FOUND,
    UNKNOWN_ERROR_WITH_SERVER
}