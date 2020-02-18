package com.rommac.cuefa.di

import com.rommac.cuefa.core.auth.AuthInteractor
import com.rommac.cuefa.core.auth.AuthInteractorImpl
import com.rommac.cuefa.di.scope.ActivityScope
import com.rommac.cuefa.ui.main.MainContract
import com.rommac.cuefa.ui.main.MainPresenter
import dagger.Binds
import dagger.Module

@Module
abstract class MainActivityModule {

    @Binds
    abstract fun provideMainPresenter(mainPresenterImpl: MainPresenter): MainContract.Presenter

    @Binds
    abstract fun provideAuthInteractor(authInteractorImpl: AuthInteractorImpl):AuthInteractor
}