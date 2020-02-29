package com.rommac.core

import com.green.coreapi.database.DatabaseProvider
import com.green.coreapi.mediator.AppProvider
import com.green.coreapi.viewmodel.ViewModelsProvider
import com.green.habits.coreimpl.DaggerDatabaseComponent
import com.green.habits.coreimpl.DaggerViewModelComponent
import com.rommac.core_api.database.DatabaseProvider
import com.rommac.core_api.mediator.AppProvider

object CoreProvidersFactory {

    fun createDatabaseBuilder(appProvider: AppProvider): DatabaseProvider {
        return DaggerDatabaseComponent.builder().appProvider(appProvider).build()
    }

}