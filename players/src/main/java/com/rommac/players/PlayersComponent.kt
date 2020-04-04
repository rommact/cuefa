package com.rommac.players

import com.rommac.core_api.ProvidersFacade
import com.rommac.core_api.scope.FragmentScope
import com.rommac.network_api.NetworkProvider
import dagger.Component

@FragmentScope
@Component(
    modules = [PlayersModule::class],
    dependencies = [ProvidersFacade::class, NetworkProvider::class]
)
interface PlayersComponent {
    companion object {
        fun create(providersFacade: ProvidersFacade, networkProvider: NetworkProvider): PlayersComponent {
            return DaggerPlayersComponent
                .builder()
                .providersFacade(providersFacade)
                .networkProvider(networkProvider)
                .build()
        }
    }

    fun inject(sessionFragment: PlayersFragment)
}