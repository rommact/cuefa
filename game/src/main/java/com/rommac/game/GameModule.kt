package com.rommac.game

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class GameModule {
    @Provides
    fun provideGameInteractor(api: GameApi): GameInteractor {
        return GameInteractorImpl(api)
    }

    @Provides
    fun getGamesApi(retrofit: Retrofit): GameApi {
        return retrofit.create(GameApi::class.java)
    }
}