package com.rommac.players.di

import android.view.View
import androidx.lifecycle.LifecycleOwner
import com.rommac.players.PlayerInteractor
import com.rommac.players.PlayerInteractorImpl
import com.rommac.players.network.PlayersApi
import com.rommac.players.PlayersViewImpl
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
    fun providePlayersView(
        rootView: View, lifecycleOwner: LifecycleOwner
    ): PlayersViewImpl {
        return PlayersViewImpl(
            rootView,
            lifecycleOwner
        )
    }

    @Provides
    fun getPlayersApi(retrofit: Retrofit): PlayersApi {
        return retrofit.create(PlayersApi::class.java)
    }

}