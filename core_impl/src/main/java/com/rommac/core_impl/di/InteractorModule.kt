package com.rommac.core_impl.di

import com.rommac.core_api.interactor.*
import com.rommac.core_api.network.Api
import com.rommac.core_impl.interactor.GameInteractorImpl
import com.rommac.core_impl.interactor.PlayerInteractorImpl
import com.rommac.core_impl.interactor.SessionInteractorImpl
import dagger.Module
import dagger.Provides
@Module
class InteractorModule {

    @Provides
    fun providePlayerInteractor(api: Api): PlayerInteractor {
        return PlayerInteractorImpl(api)
    }
    @Provides
    fun provideSessionsInteractor(api: Api): SessionInteractor {
        return SessionInteractorImpl(api)
    }

    @Provides
    fun provideGameInteractor(api: Api): GameInteractor {
        return GameInteractorImpl(api)
    }

}