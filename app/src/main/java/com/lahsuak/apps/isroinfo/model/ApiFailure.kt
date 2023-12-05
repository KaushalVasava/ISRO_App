package com.lahsuak.apps.isroinfo.model

sealed class ApiFailure {
    data class Unknown(val error: String) : ApiFailure()
}
