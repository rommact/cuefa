package com.rommac.sessions.di

import android.view.View
import androidx.lifecycle.LifecycleOwner
import com.rommac.core_api.mediator.GameMediator
import com.rommac.sessions.SessionInteractor
import com.rommac.sessions.SessionInteractorImpl
import com.rommac.sessions.SessionsViewImpl
import com.rommac.sessions.network.SessionsApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class SessionModule {
    @Provides
    fun provideSessionsInteractor(api: SessionsApi): SessionInteractor {
        return SessionInteractorImpl(api)
    }

    @Provides
    fun provideSessionView(
        rootView: View, lifecycleOwner: LifecycleOwner,
        gameMediator: GameMediator
    ): SessionsViewImpl {
        return SessionsViewImpl(
            rootView,
            lifecycleOwner,
            gameMediator
        )
    }

    @Provides
    fun getSessionApi(retrofit: Retrofit): SessionsApi {
        return retrofit.create(SessionsApi::class.java)
    }
}