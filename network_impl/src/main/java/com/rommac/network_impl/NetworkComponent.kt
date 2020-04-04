package com.rommac.network_impl

import com.rommac.core_api.AuthDataProvider
import com.rommac.core_api.mediator.AppProvider
import com.rommac.network_api.NetworkFacade
import com.rommac.network_api.NetworkProvider
import com.rommac.network_impl.NetworkModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    dependencies = [AppProvider::class],
    modules = [NetworkModule::class]
)
interface NetworkComponent: NetworkFacade {

}