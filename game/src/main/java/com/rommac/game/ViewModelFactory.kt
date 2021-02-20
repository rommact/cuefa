package com.rommac.game

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rommac.core_api.AuthDataProvider
import javax.inject.Inject
import javax.inject.Provider

class ViewModelFactory @Inject constructor(
    val gameInteractor: Provider<GameInteractor>, val authDataProvider: AuthDataProvider
): ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return GameViewModelImpl(gameInteractor.get(),authDataProvider.authData) as T
    }
}