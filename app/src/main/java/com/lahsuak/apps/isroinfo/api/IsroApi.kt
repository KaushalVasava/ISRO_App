package com.lahsuak.apps.isroinfo.api

import com.lahsuak.apps.isroinfo.model.Launch
import com.lahsuak.apps.isroinfo.model.SpaceCraft
import retrofit2.http.GET

interface IsroApi {

    @GET("launches")
    suspend fun getLaunches(): List<Launch>

    @GET("spacecraft")
    suspend fun getSpaceCraft(): List<SpaceCraft>

}