package com.rommac.auth.di

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import com.rommac.auth.AuthActivity
import com.rommac.core_api.ProvidersFacade
import com.rommac.core_api.scope.ActivityScope
import com.rommac.network_api.NetworkFacade
import dagger.BindsInstance
import dagger.Component

@ActivityScope
@Component(
    modules = [AuthModule::class, AuthModule.MainModuleProviders::class],
    dependencies = [ProvidersFacade::class, NetworkFacade::class]
)
interface AuthComponent {
    companion object {
        fun create(
            providersFacade: ProvidersFacade,
            networkFacade: NetworkFacade,
            activity: AppCompatActivity
        ): AuthComponent {
            return DaggerAuthComponent.builder()
                .activity(activity)
                .lifecycleOwner(activity)
                .providersFacade(providersFacade)
                .networkFacade(networkFacade)
                .build()

        }
    }

    fun inject(mainActivity: AuthActivity)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun lifecycleOwner(lifecycle: LifecycleOwner): Builder

        @BindsInstance
        fun activity(activity: AppCompatActivity): Builder
        fun providersFacade(providersFacade: ProvidersFacade): Builder
        fun networkFacade(networkFacade: NetworkFacade): Builder
        fun build(): AuthComponent
    }
}