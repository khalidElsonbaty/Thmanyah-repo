package com.khaled.thmanyah.core.utils

sealed class ApiResult<out T> {
    object Loading : ApiResult<Nothing>()
    data class Success<out R>(val data: R) : ApiResult<R>()
    data class Error(val exception: Throwable) : ApiResult<Nothing>()
}