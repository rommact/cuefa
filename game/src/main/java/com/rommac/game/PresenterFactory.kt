package com.rommac.cuefa.ui.game

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rommac.core_api.interactor.GameInteractor
import com.rommac.game.GamePresenterImpl
import javax.inject.Inject
import javax.inject.Provider

class PresenterFactory @Inject constructor(
    val gameInteractor: Provider<GameInteractor>
): ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return GamePresenterImpl(gameInteractor.get()) as T
    }
}