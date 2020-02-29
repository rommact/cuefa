package com.rommac.cuefa.di

import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.rommac.cuefa.App
import com.rommac.cuefa.core.auth.AuthData
import com.rommac.cuefa.core.auth.AuthDataProvider
import com.rommac.cuefa.core.auth.AuthInteractor
import com.rommac.cuefa.core.auth.AuthInteractorImpl
import com.rommac.cuefa.core.session.SessionInteractor
import com.rommac.cuefa.core.session.SessionInteractorImpl
import com.rommac.cuefa.db.AppDatabase
import com.rommac.cuefa.db.dto.PlayerDao
import com.rommac.cuefa.db.dto.PlayerItem
import com.rommac.cuefa.network.Api
import com.rommac.cuefa.network.NetworkService
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
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
                        playerDao.add(PlayerItem(null, "1", "test", true,true))
                        playerDao.add(PlayerItem(null, "2", "test2", true, false))
                        playerDao.add(PlayerItem(null, "3", "test3", true, false))
                    }

                }
            })
            .build()
    }


    @Provides
    @Singleton
    fun provideRetrofit(authDataProvider: AuthDataProvider): Retrofit {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = (HttpLoggingInterceptor.Level.BODY)
        val httpClient = OkHttpClient.Builder()
            .connectTimeout(3, TimeUnit.SECONDS)
            .readTimeout(3, TimeUnit.SECONDS)
            .writeTimeout(3, TimeUnit.SECONDS)
            .addInterceptor { chain ->
                val uid = authDataProvider.authData.uid
                val request: Request =
                    chain.request().newBuilder().addHeader("UID", uid).build()
                chain.proceed(request)
            }
            .addInterceptor(interceptor)
            .build()
        return Retrofit.Builder()
            .baseUrl(NetworkService.BASE_URL)
            .client(httpClient)
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


    @Provides
    fun provideAuthInteractor(api: Api): AuthInteractor{
        return AuthInteractorImpl(api)
    }
    @Provides
    fun provideSessionsInteractor(api: Api): SessionInteractor{
        return SessionInteractorImpl(api)
    }
    @Provides
    fun provideAuthProvider(): AuthDataProvider{
        return AuthInteractorImpl.Companion
    }

}