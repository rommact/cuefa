package com.rommac.game.di

import android.app.Activity
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import com.rommac.core_api.ProvidersFacade
import com.rommac.core_api.scope.ActivityScope
import com.rommac.game.GameActivity
import com.rommac.network_api.NetworkProvider
import dagger.BindsInstance
import dagger.Component

@ActivityScope
@Component(
    modules = [GameModule::class],
    dependencies = [ProvidersFacade::class, NetworkProvider::class]
)
interface GameComponent {
    companion object{
        fun create(activity:AppCompatActivity,providersFacade: ProvidersFacade, networkProvider: NetworkProvider): GameComponent {
            return DaggerGameComponent.builder()
                .lifecycleOwner(activity)
                .activity(activity)
                .providersFacade(providersFacade)
                .networkProvider(networkProvider)
                .build()
        }
    }

    fun inject(gameActivity: GameActivity)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun lifecycleOwner(lifecycle: LifecycleOwner): Builder
        @BindsInstance
        fun activity(activity: AppCompatActivity): Builder

        fun providersFacade(providersFacade: ProvidersFacade): Builder

        fun networkProvider(networkProvider: NetworkProvider): Builder

        fun build(): GameComponent
    }
}