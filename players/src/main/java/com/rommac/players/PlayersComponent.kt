package com.rommac.players

import com.rommac.core_api.ProvidersFacade
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [PlayersModule::class],
    dependencies = [ProvidersFacade::class]
)
interface PlayersComponent {
    companion object{
        fun create(providersFacade: ProvidersFacade): PlayersComponent{
            return DaggerPlayersComponent
                .builder()
                .providersFacade(providersFacade)
                .build()
        }
    }

    fun inject(sessionFragment: PlayersFragment)
}