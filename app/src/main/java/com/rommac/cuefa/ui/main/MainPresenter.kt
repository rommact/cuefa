package com.rommac.cuefa.ui.main

import android.app.Activity
import android.content.Intent
import com.example.lib.mvp.BasePresenter
import com.rommac.cuefa.core.auth.AuthInteractor
import com.google.firebase.auth.FirebaseAuth
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainPresenter
@Inject constructor(private val authInteractor: AuthInteractor)
    : BasePresenter<MainContract.View>(), MainContract.Presenter {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun onBottomMenuItemClicked(pos: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
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
                compositeDisposable.add(subscribe)
            } else {

            }
        }
    }

    override fun viewIsReady() {
        val authData = authInteractor.authData
        if(authData.status){
            view?.signin(authData.email)
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

    override fun destroy() {
        super.destroy()
        compositeDisposable.dispose()
    }

    companion object {
         val RC_SIGN_IN: Int = 1
    }
}