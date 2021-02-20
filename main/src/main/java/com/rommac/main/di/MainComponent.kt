package com.rommac.main.di

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import com.rommac.core_api.ProvidersFacade
import com.rommac.core_api.scope.ActivityScope
import com.rommac.main.MainActivity
import com.rommac.network_api.NetworkFacade
import dagger.BindsInstance
import dagger.Component

@ActivityScope
@Component(
    modules = [MainModule::class, MainModule.MainModuleProviders::class],
    dependencies = [ProvidersFacade::class, NetworkFacade::class]
)
interface MainComponent {
    companion object {
        fun create(providersFacade: ProvidersFacade, networkFacade: NetworkFacade, activity: AppCompatActivity): MainComponent {
            return DaggerMainComponent.builder()
                .activity(activity)
                .lifecycleOwner(activity)
                .rooView(activity.findViewById(android.R.id.content) as View)
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
        fun rooView(rootView: View): Builder
        @BindsInstance
        fun activity(activity: AppCompatActivity): Builder
        fun providersFacade(providersFacade: ProvidersFacade): Builder
        fun networkFacade(networkFacade: NetworkFacade): Builder
        fun build(): MainComponent
    }
}