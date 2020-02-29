package com.rommac.core_impl.di

import com.rommac.core_api.storage.PlayersStorage
import com.rommac.core_impl.interactor.PlayerInteractor
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryModule {

    @Binds
    abstract fun providePlayerRepository(playerInteractor: PlayerInteractor): PlayersStorage
}