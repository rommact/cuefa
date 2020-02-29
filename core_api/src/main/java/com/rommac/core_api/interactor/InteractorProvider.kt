package com.rommac.core_api.interactor

interface InteractorProvider {
    fun getSessionInteractor(): SessionInteractor
    fun getGameInteractor(): GameInteractor
    fun getPlayersInteractor(): PlayerInteractor
}