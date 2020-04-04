package com.rommac.cuefa.di

import android.app.Application
import android.content.Context
import com.rommac.core_api.AuthDataProvider
import com.rommac.core_api.mediator.AppProvider
import com.rommac.network_api.NetworkProvider
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [AppModule::class])
interface AppComponent : AppProvider {
    companion object {

        private var appComponent: AppProvider? = null

        fun create(application: Application): AppProvider {
            return appComponent ?: DaggerAppComponent
                .builder()
                .application(application.applicationContext)
                .build().also {
                    appComponent = it
                }
//            return null!!
        }
    }

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(context: Context): Builder

        fun build(): AppComponent
    }
}