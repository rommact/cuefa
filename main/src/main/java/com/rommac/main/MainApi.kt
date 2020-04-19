package com.rommac.main

import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST

interface MainApi {
    @POST("auth")
    fun auth(@Body body: AuthBody): Single<AuthResponse>

}