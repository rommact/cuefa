package com.rommac.cuefa.di

import com.rommac.auth.AuthInteractorImpl
import com.rommac.core_api.AuthDataProvider
import dagger.Module
import dagger.Provides


@Module
class AppModule {

    @Provides
    fun provideAuthProvider(): AuthDataProvider {
        return AuthInteractorImpl.Companion
    }





}