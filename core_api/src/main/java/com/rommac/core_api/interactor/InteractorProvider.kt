package com.rommac.core_api.interactor

import com.rommac.core_api.storage.PlayersStorage

interface InteractorProvider {
    fun getPlayersRepository(): PlayersStorage
    fun getSessionInteractor(): SessionInteractor

    fun getAuthDataProvider(): AuthDataProvider
}