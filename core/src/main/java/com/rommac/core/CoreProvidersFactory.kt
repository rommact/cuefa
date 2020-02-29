package com.rommac.core


import com.rommac.core_api.database.DatabaseProvider
import com.rommac.core_api.interactor.InteractorProvider
import com.rommac.core_api.mediator.AppProvider
import com.rommac.core_api.network.NetworkProvider
import com.rommac.core_api.storage.StorageProvider
import com.rommac.core_impl.di.DaggerDatabaseComponent
import com.rommac.core_impl.di.DaggerInteractorComponent
import com.rommac.core_impl.di.DaggerNetworkComponent
import com.rommac.core_impl.di.DaggerStorageComponent

object CoreProvidersFactory {

    fun createDatabaseBuilder(appProvider: AppProvider): DatabaseProvider {
        return DaggerDatabaseComponent.builder().appProvider(appProvider).build()
    }
    fun createNetworkBuilder(appProvider: AppProvider): NetworkProvider {
        return DaggerNetworkComponent.builder().appProvider(appProvider).build()
    }

    fun createInteractorBuilder(appProvider: AppProvider): InteractorProvider {
        return DaggerInteractorComponent.builder().appProvider(appProvider).build()
    }

    fun createStorageBuilder(appProvider: AppProvider): StorageProvider {
        return DaggerStorageComponent.builder().appProvider(appProvider).build()
    }


}