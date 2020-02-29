package com.rommac.core_impl.di

import com.rommac.core_api.interactor.AuthDataProvider
import com.rommac.core_api.network.Api
import com.rommac.core_api.network.NetworkService
import dagger.Module
import dagger.Provides
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


@Module
class NetworkModule {
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
}
