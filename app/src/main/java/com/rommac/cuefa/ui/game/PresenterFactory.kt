package com.rommac.cuefa.ui.game

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rommac.cuefa.interactor.GameInteractor
import com.rommac.cuefa.core.auth.AuthDataProvider
import com.rommac.cuefa.core.auth.AuthInteractor
import com.rommac.cuefa.ui.main.MainPresenter
import javax.inject.Inject
import javax.inject.Provider

class PresenterFactory @Inject constructor(
    val authInteractor: Provider<AuthInteractor>,
    val authDataProvider: Provider<AuthDataProvider>,
    val gameInteractor: Provider<GameInteractor>
): ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        when(modelClass){
            MainPresenter::class.java -> return MainPresenter(authInteractor.get(), authDataProvider.get()) as T
            GamePresenterImpl::class.java -> return GamePresenterImpl(gameInteractor.get()) as T
        }
        return MainPresenter(authInteractor.get(), authDataProvider.get()) as T
    }
}