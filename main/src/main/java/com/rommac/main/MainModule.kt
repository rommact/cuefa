package com.rommac.main

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.rommac.core_api.interactor.AuthInteractor
import com.rommac.core_api.mediator.AuthMediator
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class MainModule {
    @Module
    object MainModuleProviders{
        @JvmStatic
        @Provides
        fun provideMainPresenter(activity: AppCompatActivity,mainPresenterFactory: MainPresenterFactory): MainContract.Presenter{
            return ViewModelProvider(activity, mainPresenterFactory).get(MainPresenter::class.java)
        }
    }
    @Binds
    abstract fun bindAuthMediator(authMediatorImpl: AuthMediatorImpl): AuthMediator

    @Binds
    abstract fun bindMainView(mainView: MainView): MainContract.View

    @Binds
    abstract fun bindAuthInteractor(authInteractorImpl: AuthInteractorImpl): AuthInteractor
}