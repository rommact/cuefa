package com.rommac.core_api.network

import retrofit2.Retrofit

interface NetworkProvider {
    fun getRetrofit():Retrofit
    fun getApi():Api
}