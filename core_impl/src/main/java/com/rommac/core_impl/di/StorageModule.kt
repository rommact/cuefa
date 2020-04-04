package com.rommac.core_impl.di

import com.rommac.core_api.database.PlayerDao
import com.rommac.core_api.storage.PlayersStorage
import com.rommac.core_impl.storage.LocalPlayersStorage
import dagger.Module
import dagger.Provides

@Module
class StorageModule {

    @Provides
    fun providePlayerStorage(playerDao: PlayerDao): PlayersStorage{
        return LocalPlayersStorage(playerDao)
    }
}