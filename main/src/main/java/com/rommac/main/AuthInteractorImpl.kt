package com.rommac.main

import com.google.firebase.auth.FirebaseAuth
import com.rommac.core_api.interactor.AuthData
import com.rommac.core_api.interactor.AuthInteractor
import com.rommac.core_api.network.Api
import com.rommac.core_api.network.dto.AuthBody
import com.rommac.core_api.interactor.AuthDataProvider
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AuthInteractorImpl
@Inject constructor(private val api: Api): AuthInteractor {
    companion object: AuthDataProvider {
        private var currentAuthData: AuthData = AuthData(false, 0, "","")

        override var authData: AuthData
            get() = currentAuthData
            set(value) {}
    }

    override fun signIn():Observable<AuthData> {
        val user = FirebaseAuth.getInstance().currentUser
        val email = user?.email!!
        val name = user.displayName!!
        val token = "123"
        val uid = user.uid
        val deviceid = "123"
        val adid = "123"

        val authData = AuthBody(
            email,
            name,
            token,
            deviceid,
            uid,
            adid
        )
        return api.auth(authData)
            .map { AuthData(it.status, it.balance,email, uid) }
            .doOnNext {
                currentAuthData = it
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    }

    override fun signOut() {
        FirebaseAuth.getInstance().signOut()
    }
}