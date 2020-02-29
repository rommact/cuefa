package com.rommac.game

import com.rommac.core_api.ProvidersFacade
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [GameModule::class],
    dependencies = [ProvidersFacade::class]
)
interface GameComponent {
    companion object{
        fun create(providersFacade: ProvidersFacade): GameComponent{
            return DaggerGameComponent
                .builder()
                .providersFacade(providersFacade)
                .build()
        }
    }

    fun inject(sessionFragment: GameActivity)
}