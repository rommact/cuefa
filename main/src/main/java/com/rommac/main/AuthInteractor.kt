package com.rommac.main

import com.rommac.core_api.dto.AuthData
import io.reactivex.Single

interface AuthInteractor {

    fun signIn(): Single<AuthData>

    fun signOut()


}