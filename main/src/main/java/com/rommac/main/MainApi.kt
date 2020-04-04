package com.rommac.main

import com.rommac.core_api.network.dto.AuthResponse
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST

interface MainApi {
    @POST("auth.php")
    fun auth(@Body body: AuthBody): Single<AuthResponse>

}