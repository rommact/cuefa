package com.rommac.core_impl.di

import com.rommac.core_api.database.DatabaseProvider
import com.rommac.core_api.mediator.AppProvider
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    dependencies = [AppProvider::class],
    modules = [DatabaseModule::class]
)
interface DatabaseComponent : DatabaseProvider