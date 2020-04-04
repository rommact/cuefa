package com.rommac.core


import com.rommac.core_api.database.DatabaseProvider
import com.rommac.core_api.mediator.AppProvider
import com.rommac.core_api.storage.StorageProvider
import com.rommac.core_impl.di.DaggerDatabaseComponent
import com.rommac.core_impl.di.DaggerStorageComponent

object CoreProvidersFactory {

    fun createDatabaseBuilder(appProvider: AppProvider): DatabaseProvider {
        return DaggerDatabaseComponent.builder().appProvider(appProvider).build()
    }

    fun createStorageBuilder(appProvider: AppProvider, databaseProvider: DatabaseProvider): StorageProvider {
        return DaggerStorageComponent.builder().appProvider(appProvider).databaseProvider(databaseProvider).build()
    }


}