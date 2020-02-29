package com.rommac.core_impl.di

import com.rommac.core_api.storage.PlayersStorage
import com.rommac.core_impl.interactor.PlayerInteractorImpl
import dagger.Binds
import dagger.Module

@Module
abstract class StorageModule {

    @Binds
    abstract fun providePlayerStorage(playerInteractor: PlayerInteractorImpl): PlayersStorage
}