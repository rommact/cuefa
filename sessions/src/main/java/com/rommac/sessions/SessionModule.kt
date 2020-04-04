package com.rommac.sessions

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class SessionModule {
    @Provides
    fun provideSessionsInteractor(api: SessionsApi): SessionInteractor {
        return SessionInteractorImpl(api)
    }

    @Provides
    fun getSessionApi(retrofit: Retrofit): SessionsApi {
        return retrofit.create(SessionsApi::class.java)
    }
}