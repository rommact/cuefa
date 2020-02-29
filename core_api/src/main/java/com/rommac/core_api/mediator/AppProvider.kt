package com.rommac.core_api.mediator

import android.content.Context
import com.rommac.core_api.interactor.AuthDataProvider
import com.rommac.core_api.network.Api

interface AppProvider {

    fun provideContext(): Context
    fun provideAuthDataProvider(): AuthDataProvider
}