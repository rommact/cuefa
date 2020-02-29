package com.rommac.core_impl.di

import com.rommac.core_api.interactor.InteractorProvider
import com.rommac.core_api.mediator.AppProvider
import dagger.Component
import javax.inject.Singleton


@Component(
    dependencies = [AppProvider::class],
    modules = [InteractorModule::class]
)
interface InteractorComponent: InteractorProvider {
}