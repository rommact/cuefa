package com.rommac.main.di

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import com.rommac.core_api.mediator.AuthMediator
import com.rommac.main.*
import com.rommac.main.network.MainApi
import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
abstract class MainModule {
    @Module
    object MainModuleProviders {

        @JvmStatic
        @Provides
        fun getMainApi(retrofit: Retrofit): MainApi {
            return retrofit.create(MainApi::class.java)
        }

        @JvmStatic
        @Provides
        fun getAuthInteractor(mainApi: MainApi): AuthInteractor {
            return AuthInteractorImpl(mainApi)
        }

        @Provides
        fun provideMainView(
            activity: AppCompatActivity, authMediator: AuthMediator,
            rootView: View, lifecycleOwner: LifecycleOwner
        ): MainView {
            return MainView(
                activity, authMediator,
                rootView,
                lifecycleOwner
            )
        }

    }

    @Binds
    abstract fun bindAuthMediator(authMediatorImpl: AuthMediatorImpl): AuthMediator


}