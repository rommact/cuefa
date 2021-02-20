package com.rommac.players

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rommac.core_api.storage.PlayersStorage
import javax.inject.Inject
import javax.inject.Provider

class ViewModelFactory @Inject constructor(
    private val playersRepositories: Provider<PlayersStorage>
): ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PlayersViewModel(playersRepositories.get()) as T
    }
}