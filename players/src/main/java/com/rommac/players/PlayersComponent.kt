package com.rommac.players

import android.content.Context
import android.view.View
import androidx.lifecycle.Lifecycle
import com.rommac.core_api.ProvidersFacade
import com.rommac.core_api.scope.FragmentScope
import com.rommac.mvp.BaseFragment
import com.rommac.network_api.NetworkProvider
import dagger.BindsInstance
import dagger.Component

@FragmentScope
@Component(
    modules = [PlayersModule::class],
    dependencies = [ProvidersFacade::class, NetworkProvider::class]
)
interface PlayersComponent {
    companion object {
        fun create(providersFacade: ProvidersFacade, networkProvider: NetworkProvider, fragment: BaseFragment): PlayersComponent {
            return DaggerPlayersComponent
                .builder()
                .lifecycle(fragment.lifecycle)
                .rooView(fragment.requireView())
                .providersFacade(providersFacade)
                .networkProvider(networkProvider)
                .build()
        }
    }

    fun inject(sessionFragment: PlayersFragment)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun lifecycle(lifecycle: Lifecycle): Builder
        @BindsInstance
        fun rooView(rootView: View): Builder

        fun providersFacade(providersFacade: ProvidersFacade): Builder

        fun networkProvider(networkProvider: NetworkProvider): Builder

        fun build(): PlayersComponent
    }
}