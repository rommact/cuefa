package com.rommac.cuefa.di

import com.rommac.core_api.interactor.AuthDataProvider
import com.rommac.main.AuthInteractorImpl
import dagger.Module
import dagger.Provides


@Module
class AppModule {


    @Provides
    fun provideAuthProvider(): AuthDataProvider {
        return AuthInteractorImpl.Companion
    }

}