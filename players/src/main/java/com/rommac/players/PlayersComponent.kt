package com.rommac.players

import com.rommac.core_api.ProvidersFacade
import com.rommac.core_api.scope.FragmentScope
import dagger.Component

@FragmentScope
@Component(
    modules = [PlayersModule::class],
    dependencies = [ProvidersFacade::class]
)
interface PlayersComponent {
    companion object {
        fun create(providersFacade: ProvidersFacade): PlayersComponent {
            return DaggerPlayersComponent
                .builder()
                .providersFacade(providersFacade)
                .build()
        }
    }

    fun inject(sessionFragment: PlayersFragment)
}