package com.rommac.core_api.mediator

import android.content.Context
import com.rommac.core_api.AuthDataProvider

interface AppProvider {

    fun provideContext(): Context
    fun provideAuthDataProvider(): AuthDataProvider
}