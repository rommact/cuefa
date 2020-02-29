package com.rommac.cuefa.ui.session

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rommac.cuefa.core.session.SessionInteractor
import com.rommac.cuefa.repository.PlayersRepository
import javax.inject.Inject
import javax.inject.Provider

class SessionPresenterFactory @Inject constructor(
    private val sessionInteractor:Provider<SessionInteractor>): ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SessionsPresenter(sessionInteractor.get()) as T
    }
}