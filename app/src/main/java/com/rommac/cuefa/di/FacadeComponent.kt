package com.rommac.cuefa.di

import android.app.Application
import com.rommac.core.CoreProvidersFactory
import com.rommac.core_api.ProvidersFacade
import com.rommac.core_api.database.DatabaseProvider
import com.rommac.core_api.mediator.AppProvider
import com.rommac.core_api.storage.StorageProvider
import com.rommac.cuefa.App
import com.rommac.main.di.MediatorsBindings
import dagger.Component

@Component(
    dependencies = [AppProvider::class, DatabaseProvider::class,
        StorageProvider::class]
)
interface FacadeComponent : ProvidersFacade {

    companion object {

        fun init(application: Application): FacadeComponent {
            val dataBaseProvider =
                CoreProvidersFactory.createDatabaseBuilder(AppComponent.create(application))
            return DaggerFacadeComponent.builder()
                .appProvider(AppComponent.create(application))
                .databaseProvider(dataBaseProvider)
                .storageProvider(
                    CoreProvidersFactory.createStorageBuilder(
                        AppComponent.create(
                            application
                        ), dataBaseProvider
                    )
                )
                .build()
        }
    }

    fun inject(app: App)
}