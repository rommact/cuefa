package com.rommac.main.di

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController
import com.rommac.core_api.ProvidersFacade
import com.rommac.core_api.mediator.MediatorsProvider
import com.rommac.core_api.scope.ActivityScope
import com.rommac.main.MainActivity
import com.rommac.network_api.NetworkFacade
import dagger.BindsInstance
import dagger.Component

@ActivityScope
@Component(
    modules = [MainModule::class, MainModule.MainModuleProviders::class, MediatorsBindings::class],
    dependencies = [ProvidersFacade::class, NetworkFacade::class]
)
interface MainComponent: MediatorsProvider {
    companion object {
        fun create(providersFacade: ProvidersFacade, networkFacade: NetworkFacade, activity: AppCompatActivity): MainComponent {
            return DaggerMainComponent.builder()
                .activity(activity)
                .lifecycleOwner(activity)
                .providersFacade(providersFacade)
                .networkFacade(networkFacade)
                .build()

        }
    }

    fun inject(mainActivity: MainActivity)
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun lifecycleOwner(lifecycle: LifecycleOwner): Builder
        @BindsInstance
        fun activity(activity: AppCompatActivity): Builder
        fun providersFacade(providersFacade: ProvidersFacade): Builder
        fun networkFacade(networkFacade: NetworkFacade): Builder
        fun build(): MainComponent
    }
}