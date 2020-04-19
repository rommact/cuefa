package com.rommac.sessions

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rommac.core_api.AuthDataProvider
import com.rommac.core_api.dto.AuthData
import javax.inject.Inject
import javax.inject.Provider

class SessionPresenterFactory @Inject constructor(
    private val sessionInteractor: Provider<SessionInteractor>, private val authDataProvider: AuthDataProvider
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SessionsPresenter(sessionInteractor.get(), authDataProvider) as T
    }
}