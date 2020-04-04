package com.rommac.main

import androidx.appcompat.app.AppCompatActivity
import com.rommac.core_api.ProvidersFacade
import com.rommac.core_api.scope.ActivityScope
import com.rommac.network_api.NetworkFacade
import com.rommac.network_api.NetworkProvider
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@ActivityScope
@Component(
    modules = [MainModule::class, MainModule.MainModuleProviders::class],
    dependencies = [ProvidersFacade::class, NetworkFacade::class]
)
interface MainComponent {
    companion object {
        fun create(providersFacade: ProvidersFacade, networkFacade: NetworkFacade, activity: AppCompatActivity): MainComponent {
            return DaggerMainComponent
                .builder()
                .activity(activity)
                .providersFacade(providersFacade)
                .networkFacade(networkFacade)
                .build()

        }
    }

    fun inject(mainActivity: MainActivity)
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun activity(activity: AppCompatActivity): Builder
        fun providersFacade(providersFacade: ProvidersFacade): Builder
        fun networkFacade(networkFacade: NetworkFacade): Builder
        fun build(): MainComponent
    }
}