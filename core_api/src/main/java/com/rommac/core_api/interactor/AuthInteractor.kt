package com.rommac.core_api.interactor

import io.reactivex.Observable

interface AuthInteractor {

    fun signIn():Observable<AuthData>

    fun signOut()


}