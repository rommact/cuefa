package com.rommac.cuefa.di

import com.rommac.cuefa.ui.players.PlayersContract
import com.rommac.cuefa.ui.players.PlayersPresenter
import dagger.Binds
import dagger.Module

@Module
abstract class PlayersFragmentModule {

    @Binds
    abstract fun providePlayersPresenter(playersPresenter: PlayersPresenter): PlayersContract.Presenter
}