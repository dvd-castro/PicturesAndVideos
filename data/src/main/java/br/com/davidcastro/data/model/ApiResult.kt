package br.com.davidcastro.data.model

sealed class ApiResult<out T> {
    data class onSuccess<out T>(val data: T) : ApiResult<T>()
    data class onError(val error: Throwable) : ApiResult<Nothing>()
    object onLoading : ApiResult<Nothing>()
}