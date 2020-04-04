package com.rommac.sessions

import com.rommac.core_api.ProvidersFacade
import com.rommac.core_api.scope.FragmentScope
import com.rommac.network_api.NetworkProvider
import dagger.Component


@FragmentScope
@Component(
    modules = [SessionModule::class],
    dependencies = [ProvidersFacade::class, NetworkProvider::class]
)
interface SessionsComponent {
    companion object {
        fun create(providersFacade: ProvidersFacade, networkProvider: NetworkProvider): SessionsComponent {
            return DaggerSessionsComponent
                .builder()
                .providersFacade(providersFacade)
                .networkProvider(networkProvider)
                .build()
        }
    }

    fun inject(sessionFragment: SessionFragment)
}