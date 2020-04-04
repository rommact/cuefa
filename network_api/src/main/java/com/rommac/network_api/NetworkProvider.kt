package com.rommac.network_api

import retrofit2.Retrofit


interface NetworkProvider {
    fun getRetrofit(): Retrofit
}