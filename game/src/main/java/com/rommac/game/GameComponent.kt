package com.rommac.game

import com.rommac.core_api.ProvidersFacade
import com.rommac.core_api.scope.ActivityScope
import com.rommac.network_api.NetworkProvider
import dagger.Component
import javax.inject.Singleton

@ActivityScope
@Component(
    modules = [GameModule::class],
    dependencies = [ProvidersFacade::class, NetworkProvider::class]
)
interface GameComponent {
    companion object{
        fun create(providersFacade: ProvidersFacade, networkProvider: NetworkProvider): GameComponent{
            return DaggerGameComponent
                .builder()
                .providersFacade(providersFacade)
                .networkProvider(networkProvider)
                .build()
        }
    }

    fun inject(gameActivity: GameActivity)
}