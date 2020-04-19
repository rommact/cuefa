package com.rommac.main.di

import com.rommac.core_api.mediator.GameMediator
import com.rommac.game.GameMediatorImpl
import dagger.Binds
import dagger.Module

@Module
interface MediatorsBindings {

    @Binds
    fun bindGameMediator(gameMediator: GameMediatorImpl): GameMediator
}