package com.rommac.network

import com.rommac.core_api.mediator.AppProvider
import com.rommac.network_api.NetworkFacade
import com.rommac.network_api.NetworkProvider
import com.rommac.network_impl.DaggerNetworkComponent

object NetworkProviderFactory {

    fun createNetworkBuilder(appProvider: AppProvider): NetworkFacade {
        return DaggerNetworkComponent.builder().appProvider(appProvider).build()
    }

}