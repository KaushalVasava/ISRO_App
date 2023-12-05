package com.lahsuak.apps.isroinfo.model

sealed class BaseState<out DATA : Any, out ERROR : Any> {
    object Loading : BaseState<Nothing, Nothing>()
    data class Success<out DATA : Any>(val data: DATA) : BaseState<DATA, Nothing>()
    data class Failed<out ERROR : Any>(val error: ERROR) : BaseState<Nothing, ERROR>()
}