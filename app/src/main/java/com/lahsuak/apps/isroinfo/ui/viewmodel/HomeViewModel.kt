package com.lahsuak.apps.isroinfo.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lahsuak.apps.isroinfo.api.ApiClient
import com.lahsuak.apps.isroinfo.model.Launch
import com.lahsuak.apps.isroinfo.model.SpaceCraft
import com.lahsuak.apps.isroinfo.repo.IsroRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    private val repo: IsroRepo by lazy {
        IsroRepo(ApiClient.apiInstance)
    }

    private val _launches = MutableStateFlow<List<Launch>>(emptyList())
    val launches: StateFlow<List<Launch>>
        get() = _launches.asStateFlow()
    private val _spaceCrafts = MutableStateFlow<List<SpaceCraft>>(emptyList())
    val spaceCrafts: StateFlow<List<SpaceCraft>>
        get() = _spaceCrafts.asStateFlow()

    init {
        getLaunches()
        getSpaceCrafts()
    }

    private fun getLaunches() {
        viewModelScope.launch {
            try {
                _launches.value = repo.getLaunches()
            } catch (e: Exception) {
                e.stackTrace
                _launches.value = emptyList()
            }
        }
    }

    private fun getSpaceCrafts() {
        viewModelScope.launch {
            try {
                _spaceCrafts.value = repo.getSpaceCraft()
            } catch (e: Exception) {
                _spaceCrafts.value = emptyList()
            }
        }
    }
}