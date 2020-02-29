package com.rommac.cuefa.ui.main

import android.app.Activity
import android.content.Intent
import com.example.lib.mvp.BasePresenter
import com.rommac.cuefa.core.auth.AuthInteractor
import com.google.firebase.auth.FirebaseAuth
import com.rommac.cuefa.core.auth.AuthData
import com.rommac.cuefa.core.auth.AuthDataProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainPresenter
@Inject constructor(private val authInteractor: AuthInteractor, private val authDataProvider: AuthDataProvider)
    : BasePresenter<MainContract.View>(), MainContract.Presenter {


    override fun onBottomMenuItemClicked(pos: MainContract.BOTTOM_NAV) {
        when(pos){
            MainContract.BOTTOM_NAV.SESSIONS -> view?.toSessions()
            MainContract.BOTTOM_NAV.PLAYERS -> view?.toPlayers()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == MainContract.Presenter.RC_SIGN_IN) {

            if (resultCode == Activity.RESULT_OK) {
                view?.showProgressBar()
                val subscribe =
                    authInteractor.signIn()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        view?.hideProgressBar()
                        view?.signin(it.email)
                    }, {
                        view?.hideProgressBar()
                        FirebaseAuth.getInstance().signOut()
                        view?.signout()
                    })
                disposable(subscribe)
            } else {

            }
        }
    }

    override fun viewIsReady() {
        if(authDataProvider.authData.status){
            view?.signin(authDataProvider.authData.email)
        }else{
            view?.signout()
        }
    }

    override fun onSignoutClicked() {
        authInteractor.signOut()
        view?.signout()
    }

    override fun onAuthClicked() {
        view?.toAuth()
    }

    override fun onBackPressed() {

    }


    companion object {
         val RC_SIGN_IN: Int = 1
    }
}