package com.rommac.core_impl.di

import com.rommac.core_api.database.DatabaseProvider
import com.rommac.core_api.mediator.AppProvider
import com.rommac.core_api.storage.StorageProvider
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    dependencies = [AppProvider::class, DatabaseProvider::class],
    modules = [StorageModule::class]
)
interface StorageComponent: StorageProvider {
}