package com.rommac.game.di

import android.view.View
import androidx.lifecycle.LifecycleOwner
import com.rommac.game.network.GameApi
import com.rommac.game.GameInteractor
import com.rommac.game.GameInteractorImpl
import com.rommac.game.GameViewImpl
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
    fun provideGameView(
        rootView: View, lifecycleOwner: LifecycleOwner
    ): GameViewImpl {
        return GameViewImpl(
            rootView,
            lifecycleOwner
        )
    }

    @Provides
    fun getGamesApi(retrofit: Retrofit): GameApi {
        return retrofit.create(GameApi::class.java)
    }
}