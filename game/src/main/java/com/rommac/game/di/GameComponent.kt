package com.rommac.game.di

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
                .rooView(activity.findViewById(android.R.id.content) as View)
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
        fun rooView(rootView: View): Builder

        fun providersFacade(providersFacade: ProvidersFacade): Builder

        fun networkProvider(networkProvider: NetworkProvider): Builder

        fun build(): GameComponent
    }
}