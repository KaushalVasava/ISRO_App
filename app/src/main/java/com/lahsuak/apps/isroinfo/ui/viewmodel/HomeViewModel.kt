package com.lahsuak.apps.isroinfo.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lahsuak.apps.isroinfo.api.ApiClient
import com.lahsuak.apps.isroinfo.model.ApiFailure
import com.lahsuak.apps.isroinfo.model.BaseState
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

    private val _launches =
        MutableStateFlow<BaseState<List<Launch>, ApiFailure>>(BaseState.Loading)
    val launches: StateFlow<BaseState<List<Launch>, ApiFailure>>
        get() = _launches.asStateFlow()
    private val _spaceCrafts =
        MutableStateFlow<BaseState<List<SpaceCraft>, ApiFailure>>(BaseState.Loading)
    val spaceCrafts: StateFlow<BaseState<List<SpaceCraft>, ApiFailure>>
        get() = _spaceCrafts.asStateFlow()

    init {
        getLaunches()
        getSpaceCrafts()
    }

    fun getLaunches() {
        viewModelScope.launch {
            try {
                _launches.value = BaseState.Success(repo.getLaunches())
            } catch (e: Exception) {
                e.stackTrace
                _launches.value =
                    BaseState.Failed(ApiFailure.Unknown(e.message ?: "Something went wrong!"))
            }
        }
    }

    fun getSpaceCrafts() {
        viewModelScope.launch {
            try {
                _spaceCrafts.value = BaseState.Success(repo.getSpaceCraft())
            } catch (e: Exception) {
                _spaceCrafts.value =
                    BaseState.Failed(ApiFailure.Unknown(e.message ?: "Something went wrong!"))
            }
        }
    }
}