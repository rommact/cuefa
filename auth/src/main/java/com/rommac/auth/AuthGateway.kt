package com.rommac.auth

import com.rommac.auth.network.AuthBody
import com.rommac.auth.network.AuthResponse
import com.rommac.core_api.dto.AuthData
import io.reactivex.Single
import retrofit2.http.Body

interface AuthGateway {
    fun auth(@Body body: AuthBody): Single<AuthData>
}