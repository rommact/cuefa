package com.rommac.cuefa.di

import com.rommac.cuefa.repository.PlayersRepository
import com.rommac.cuefa.repository.LocalPlayersStorage
import com.rommac.cuefa.db.dto.PlayerDao
import com.rommac.cuefa.di.scope.FragmentScope
import com.rommac.cuefa.interactor.PlayerInteractor
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class RepositoryModule {

    @Binds
    abstract fun providePlayerRepository(playerInteractor: PlayerInteractor): PlayersRepository
}