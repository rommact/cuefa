package com.rommac.core_impl.di

import com.rommac.core_api.interactor.InteractorProvider
import com.rommac.core_api.mediator.AppProvider
import com.rommac.core_api.network.NetworkProvider
import com.rommac.core_api.storage.StorageProvider
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(
    dependencies = [AppProvider::class],
    modules = [InteractorModule::class, NetworkModule::class]
)
interface InteractorComponent: InteractorProvider {
}