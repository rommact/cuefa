package com.rommac.cuefa.core.auth

import io.reactivex.Observable

interface AuthInteractor {

    var authData:AuthData

    fun signIn():Observable<AuthData>

    fun signOut()


}