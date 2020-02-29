package com.rommac.cuefa.di

import com.rommac.cuefa.repository.PlayersRepository
import com.rommac.cuefa.repository.LocalPlayersStorage
import com.rommac.cuefa.db.dto.PlayerDao
import com.rommac.cuefa.di.scope.FragmentScope
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class RepositoryModule {

    @Binds
    abstract fun providePlayerRepository(localPlayersStorage: LocalPlayersStorage): PlayersRepository
}