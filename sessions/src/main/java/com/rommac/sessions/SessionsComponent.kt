package com.rommac.sessions

import com.rommac.core_api.ProvidersFacade
import com.rommac.core_api.scope.FragmentScope
import dagger.Component


@FragmentScope
@Component(
    modules = [SessionModule::class],
    dependencies = [ProvidersFacade::class]
)
interface SessionsComponent {
    companion object {
        fun create(providersFacade: ProvidersFacade): SessionsComponent {
            return DaggerSessionsComponent
                .builder()
                .providersFacade(providersFacade)
                .build()
        }
    }

    fun inject(sessionFragment: SessionFragment)
}