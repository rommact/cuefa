package com.rommac.main

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.rommac.core_api.mediator.AuthMediator
import com.rommac.cuefa.ui.main.MainPresenter
import com.rommac.cuefa.ui.main.MainPresenterFactory
import com.rommac.cuefa.ui.main.MainView
import dagger.Binds
import dagger.Provides

abstract class MainModule {

    companion object{
        @Provides
        fun provideMainPresenter(activity: AppCompatActivity,mainPresenterFactory: MainPresenterFactory): MainContract.Presenter{
            return ViewModelProvider(activity, mainPresenterFactory).get(MainPresenter::class.java)
        }
    }
    @Binds
    abstract fun bindAuthMediator(authMediatorImpl: AuthMediatorImpl): AuthMediator

}