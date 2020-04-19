package com.rommac.sessions.di

import android.view.View
import androidx.lifecycle.Lifecycle
import com.rommac.core_api.ProvidersFacade
import com.rommac.core_api.scope.FragmentScope
import com.rommac.mvp.BaseFragment
import com.rommac.mvp.CommonView
import com.rommac.network_api.NetworkProvider
import com.rommac.sessions.SessionFragment
import dagger.BindsInstance
import dagger.Component


@FragmentScope
@Component(
    modules = [SessionModule::class],
    dependencies = [ProvidersFacade::class, NetworkProvider::class]
)
interface SessionsComponent {
    companion object {
        fun create(providersFacade: ProvidersFacade, networkProvider: NetworkProvider, baseFragment: BaseFragment): SessionsComponent {
            return DaggerSessionsComponent.builder()
                .lifecycle(baseFragment.lifecycle)
                .rooView(baseFragment.requireView())
                .commonView(baseFragment.commonView)
                .providersFacade(providersFacade)
                .networkProvider(networkProvider)
                .build()
        }
    }

    fun inject(sessionFragment: SessionFragment)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun lifecycle(lifecycle: Lifecycle): Builder
        @BindsInstance
        fun rooView(rootView: View): Builder

        @BindsInstance
        fun commonView(commonView: CommonView): Builder

        fun providersFacade(providersFacade: ProvidersFacade): Builder

        fun networkProvider(networkProvider: NetworkProvider): Builder

        fun build(): SessionsComponent
    }
}