package com.rommac.cuefa.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.rommac.cuefa.App
import com.rommac.cuefa.db.AppDatabase
import com.rommac.cuefa.db.dto.PlayerDao
import com.rommac.cuefa.db.dto.PlayerItem
import com.rommac.cuefa.network.Api
import com.rommac.cuefa.network.NetworkService
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
class AppModule {

    @Provides
    @Singleton
    fun provideDatabase(context: App): AppDatabase {
        return Room
            .databaseBuilder(context, AppDatabase::class.java, "database")
            .addCallback(object : RoomDatabase.Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    GlobalScope.launch {
                        val playerDao = provideDatabase(context)
                            .playerDao()
                        playerDao.add(PlayerItem(null, "1", "test", true))
                        playerDao.add(PlayerItem(null, "2", "test2", true))
                        playerDao.add(PlayerItem(null, "3", "test3", true))
                    }

                }
            })
            .build()
    }


    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(NetworkService.BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun getApi(retrofit: Retrofit): Api {
        return retrofit.create(Api::class.java)
    }

    @Provides
    @Singleton
    fun providePlayerDao(appDatabase: AppDatabase): PlayerDao {
        return appDatabase.playerDao()
    }


}