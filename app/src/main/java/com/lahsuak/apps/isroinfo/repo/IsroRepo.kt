package com.lahsuak.apps.isroinfo.repo

import com.lahsuak.apps.isroinfo.api.IsroApi
import com.lahsuak.apps.isroinfo.model.Launch
import com.lahsuak.apps.isroinfo.model.SpaceCraft

class IsroRepo(private val api: IsroApi) {
    suspend fun getLaunches(): List<Launch> {
        return api.getLaunches()
    }
    suspend fun getSpaceCraft(): List<SpaceCraft> {
        return api.getSpaceCraft()
    }
}