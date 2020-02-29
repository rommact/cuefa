package com.rommac.cuefa.di

import com.rommac.cuefa.App
import com.rommac.cuefa.core.auth.AuthDataProvider
import com.rommac.cuefa.core.session.SessionInteractor
import com.rommac.cuefa.db.AppDatabase
import com.rommac.cuefa.network.Api
import com.rommac.cuefa.repository.PlayersRepository
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [AppModule::class, RepositoryModule::class])
interface AppComponent{
    fun getDB(): AppDatabase
    fun geApi(): Api
    fun getPlayersRepository(): PlayersRepository
    fun getSessionInteractor(): SessionInteractor

    fun getAuthDataProvider(): AuthDataProvider

    @Component.Builder
     interface Builder {
        @BindsInstance
        fun application(app: App): Builder
        fun build(): AppComponent
    }
}