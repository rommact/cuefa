package com.rommac.cuefa.ui.players

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rommac.cuefa.repository.PlayersRepository
import javax.inject.Inject
import javax.inject.Provider

class PlayerPresenterFactory @Inject constructor(
    private val playersRepositories:Provider<PlayersRepository>): ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PlayersPresenter(playersRepositories.get()) as T
    }
}