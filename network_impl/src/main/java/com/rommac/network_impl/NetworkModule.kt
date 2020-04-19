package com.rommac.network_impl

import com.google.gson.annotations.SerializedName
import com.rommac.core_api.AuthDataProvider
import dagger.Module
import dagger.Provides
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Type


@Module
class NetworkModule {
    companion object{
        const val BASE_URL = "http://192.168.1.175:8080/"
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
            .baseUrl(BASE_URL)
            .client(httpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}
