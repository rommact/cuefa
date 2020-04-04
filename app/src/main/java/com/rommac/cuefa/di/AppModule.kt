package com.rommac.cuefa.di

import com.rommac.core_api.AuthDataProvider
import com.rommac.core_api.mediator.AppProvider
import com.rommac.main.AuthInteractorImpl
import com.rommac.network.NetworkProviderFactory
import com.rommac.network_api.NetworkProvider
import dagger.Module
import dagger.Provides


@Module
class AppModule {

    @Provides
    fun provideAuthProvider(): AuthDataProvider {
        return AuthInteractorImpl.Companion
    }

}