package com.rommac.auth

import com.rommac.auth.network.AuthBody
import com.rommac.auth.network.MainApi
import com.rommac.core_api.dto.AuthData
import io.reactivex.Single
import javax.inject.Inject

class AuthGatewayImpl @Inject constructor(val authApi: MainApi): AuthGateway {
    override fun auth(body: AuthBody): Single<AuthData> {
        return authApi.auth(body)
            .map { AuthData(it.status, it.balance, body.email, body.uid) }
    }
}