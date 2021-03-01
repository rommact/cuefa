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
        const val BASE_URL = "http://192.168.1.66:8080/"
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
            .addConverterFactory(EnumConverterFactory())
            .build()
    }

    class EnumConverterFactory : Converter.Factory() {
        override fun stringConverter(type: Type?, annotations: Array<out Annotation>?,
                                     retrofit: Retrofit?): Converter<*, String>? {
            if (type is Class<*> && type.isEnum) {
                return Converter<Any?, String> { value -> getSerializedNameValue(value as Enum<*>) }
            }
            return null
        }
        fun <E : Enum<*>> getSerializedNameValue(e: E): String {
            try {
                return e.javaClass.getField(e.name).getAnnotation(SerializedName::class.java).value
            } catch (exception: NoSuchFieldException) {
                exception.printStackTrace()
            }

            return ""
        }
    }

}
