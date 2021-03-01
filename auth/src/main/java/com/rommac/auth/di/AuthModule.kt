package com.rommac.auth.di

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.rommac.auth.*
import com.rommac.auth.databinding.ActivityAuthBinding
import com.rommac.auth.network.MainApi
import com.rommac.core_api.mediator.AuthMediator
import com.rommac.core_api.scope.ActivityScope
import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
abstract class AuthModule {
    @Module
    object MainModuleProviders {

        @JvmStatic
        @Provides
        fun getMainApi(retrofit: Retrofit): MainApi {
            return retrofit.create(MainApi::class.java)
        }

        @JvmStatic
        @Provides
        fun getAuthInteractor(authGateway: AuthGateway): AuthInteractor {
            return AuthInteractorImpl(authGateway)
        }

        @JvmStatic
        @Provides
        fun provideAuthView(
            activity: AppCompatActivity, binding: ActivityAuthBinding, authMediator: AuthMediator,
            lifecycleOwner: LifecycleOwner
        ): AuthView {
            return AuthView(
                activity,
                binding,
                authMediator,
                lifecycleOwner
            )
        }


        @JvmStatic
        @Provides
        fun provideAuthViewModel(
            viewModelStoreOwner: ViewModelStoreOwner,
            viewModelFactory: ViewModelFactory
        ): AuthViewModel {
            return ViewModelProvider(
                viewModelStoreOwner,
                viewModelFactory
            ).get(AuthViewModel::class.java)
        }

        @JvmStatic
        @Provides
        fun provideViewModelStoreOwner(
            activity: AppCompatActivity
        ): ViewModelStoreOwner {
            return activity
        }

        @ActivityScope
        @JvmStatic
        @Provides
        fun provideActivityAuthBinding(
            activity: AppCompatActivity
        ): ActivityAuthBinding {
            return ActivityAuthBinding.inflate(activity.layoutInflater)
        }
    }

    @Binds
    abstract fun bindAuthMediator(authMediatorImpl: AuthMediatorImpl): AuthMediator

    @Binds
    abstract fun bindAuthGateway(authGateway: AuthGatewayImpl): AuthGateway


}