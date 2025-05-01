package com.example.courses.entities

sealed class ApiResult<out T> {

    val isSuccess = this is Success

    data object Loading : ApiResult<Nothing>()

    data class Success<out T>(val data: T): ApiResult<T>()

    data class Failure(val exception: Exception): ApiResult<Nothing>()
}