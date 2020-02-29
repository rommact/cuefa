package com.rommac.core_impl.di

import android.content.Context
import androidx.room.Room
import com.rommac.core_api.database.DataBaseContract
import com.rommac.core_api.database.PlayerDao
import com.rommac.core_impl.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.Reusable
import javax.inject.Singleton

private const val DATABASE_NAME = "main_db"

@Module
class DatabaseModule {

    @Provides
    @Reusable
    fun providePlayerDao(dataBaseContract: DataBaseContract): PlayerDao {
        return dataBaseContract.playerDao()
    }

    @Provides
    @Singleton
    fun provideDatabase(context: Context): DataBaseContract {
        return Room
            .databaseBuilder(context, AppDatabase::class.java,
                DATABASE_NAME
            )
            .build()
    }
}