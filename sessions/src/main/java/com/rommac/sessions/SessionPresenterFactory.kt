package com.rommac.sessions

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider

class SessionPresenterFactory @Inject constructor(
    private val sessionInteractor: Provider<SessionInteractor>
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SessionsPresenter(sessionInteractor.get()) as T
    }
}