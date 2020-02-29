package com.rommac.core_impl.di

import com.rommac.core_api.mediator.AppProvider
import com.rommac.core_api.network.NetworkProvider
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    dependencies = [AppProvider::class],
    modules = [NetworkModule::class]
)
interface NetworkComponent: NetworkProvider {

}