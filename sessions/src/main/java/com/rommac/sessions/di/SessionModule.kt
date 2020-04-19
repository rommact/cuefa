package com.rommac.sessions.di

import android.view.View
import androidx.lifecycle.Lifecycle
import com.rommac.core_api.mediator.GameMediator
import com.rommac.mvp.CommonView
import com.rommac.sessions.*
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
    fun provideSessionView( rootView: View,  lifecycle: Lifecycle,
                            gameMediator: GameMediator, commonView: CommonView
    ): SessionsContract.View {
        return SessionsViewImpl(
            rootView,
            lifecycle,
            gameMediator,
            commonView
        )
    }

    @Provides
    fun getSessionApi(retrofit: Retrofit): SessionsApi {
        return retrofit.create(SessionsApi::class.java)
    }
}