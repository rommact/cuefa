package com.rommac.cuefa.di

import android.app.Application
import com.rommac.core.CoreProvidersFactory
import com.rommac.core_api.ProvidersFacade
import com.rommac.core_api.database.DatabaseProvider
import com.rommac.core_api.interactor.InteractorProvider

import com.rommac.core_api.mediator.AppProvider
import com.rommac.core_api.network.NetworkProvider
import com.rommac.core_api.storage.StorageProvider
import com.rommac.cuefa.App
import dagger.Component

@Component(
    dependencies = [AppProvider::class, DatabaseProvider::class,
        StorageProvider::class, NetworkProvider::class, InteractorProvider::class]
)
interface FacadeComponent : ProvidersFacade {

    companion object {

        fun init(application: Application): FacadeComponent =
            DaggerFacadeComponent.builder()
                .appProvider(AppComponent.create(application))
                .networkProvider(CoreProvidersFactory.createNetworkBuilder(AppComponent.create(application)))
                .databaseProvider(CoreProvidersFactory.createDatabaseBuilder(AppComponent.create(application)))
                .storageProvider(CoreProvidersFactory.createStorageBuilder(AppComponent.create(application)))
                .interactorProvider(CoreProvidersFactory.createInteractorBuilder(AppComponent.create(application)))
                .build()
    }

    fun inject(app: App)
}