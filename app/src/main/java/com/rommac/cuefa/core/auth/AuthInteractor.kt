package com.rommac.cuefa.core.auth

import io.reactivex.Observable

interface AuthInteractor {

    fun signIn():Observable<AuthData>

    fun signOut()


}