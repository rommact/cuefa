package com.rommac.sessions.di

import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import com.rommac.core_api.ProvidersFacade
import com.rommac.core_api.mediator.MediatorsProvider
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
    dependencies = [ProvidersFacade::class, NetworkProvider::class, MediatorsProvider::class]
)
interface SessionsComponent {
    companion object {
        fun create(providersFacade: ProvidersFacade, networkProvider: NetworkProvider,
                   baseFragment: BaseFragment, mediatorsProvider: MediatorsProvider): SessionsComponent {
            return DaggerSessionsComponent.builder()
                .lifecycleOwner(baseFragment.viewLifecycleOwner)
                .fragment(baseFragment)
                .commonView(baseFragment.commonView)
                .providersFacade(providersFacade)
                .mediatorsProvider(mediatorsProvider)
                .networkProvider(networkProvider)
                .build()
        }
    }

    fun inject(sessionFragment: SessionFragment)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun lifecycleOwner(lifecycle: LifecycleOwner): Builder
        @BindsInstance
        fun fragment(fragment: Fragment): Builder

        @BindsInstance
        fun commonView(commonView: CommonView): Builder

        fun providersFacade(providersFacade: ProvidersFacade): Builder
        fun mediatorsProvider(providers: MediatorsProvider): Builder

        fun networkProvider(networkProvider: NetworkProvider): Builder

        fun build(): SessionsComponent
    }
}