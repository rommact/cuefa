package com.rommac.core_impl.di

import com.rommac.core_api.interactor.AuthDataProvider
import com.rommac.core_api.interactor.AuthInteractor
import com.rommac.core_api.interactor.SessionInteractor
import com.rommac.core_api.network.Api
import com.rommac.main.AuthInteractorImpl
import com.rommac.core_impl.interactor.SessionInteractorImpl
import dagger.Module
import dagger.Provides
@Module
class InteractorModule {
    @Provides
    fun provideAuthInteractor(api: Api): AuthInteractor {
        return com.rommac.main.AuthInteractorImpl(api)
    }
    @Provides
    fun provideSessionsInteractor(api: Api): SessionInteractor {
        return SessionInteractorImpl(api)
    }
    @Provides
    fun provideAuthProvider(): AuthDataProvider {
        return com.rommac.main.AuthInteractorImpl.Companion
    }
}