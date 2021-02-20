package com.rommac.players.di

import android.view.View
import androidx.lifecycle.LifecycleOwner
import com.rommac.core_api.ProvidersFacade
import com.rommac.core_api.scope.FragmentScope
import com.rommac.mvp.BaseFragment
import com.rommac.network_api.NetworkProvider
import com.rommac.players.PlayersFragment
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
            return DaggerPlayersComponent.builder()
                .lifecycleOwner(fragment.viewLifecycleOwner)
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
        fun lifecycleOwner(lifecycle: LifecycleOwner): Builder
        @BindsInstance
        fun rooView(rootView: View): Builder

        fun providersFacade(providersFacade: ProvidersFacade): Builder

        fun networkProvider(networkProvider: NetworkProvider): Builder

        fun build(): PlayersComponent
    }
}