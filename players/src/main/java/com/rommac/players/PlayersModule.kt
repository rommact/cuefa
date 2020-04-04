package com.rommac.players

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class PlayersModule {
    @Provides
    fun providePlayerInteractor(api: PlayersApi): PlayerInteractor {
        return PlayerInteractorImpl(api)
    }


    @Provides
    fun getPlayersApi(retrofit: Retrofit): PlayersApi {
        return retrofit.create(PlayersApi::class.java)
    }

}