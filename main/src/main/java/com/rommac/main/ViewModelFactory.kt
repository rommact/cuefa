package com.rommac.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rommac.core_api.AuthDataProvider
import javax.inject.Inject
import javax.inject.Provider

class ViewModelFactory @Inject constructor(
    val authInteractor:Provider<AuthInteractor>, private val authDataProvider: Provider<AuthDataProvider>): ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(authInteractor.get(), authDataProvider.get()) as T
    }
}