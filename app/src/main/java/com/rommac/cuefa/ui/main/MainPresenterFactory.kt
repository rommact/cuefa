package com.rommac.cuefa.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rommac.cuefa.core.auth.AuthInteractor
import javax.inject.Inject
import javax.inject.Provider

class MainPresenterFactory @Inject constructor(
    val authInteractor:Provider<AuthInteractor>): ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainPresenter(authInteractor.get()) as T
    }
}