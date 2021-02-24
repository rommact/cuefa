package com.rommac.auth

import android.app.Activity
import android.content.Intent
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rommac.core_api.Event
import com.rommac.core_api.mediator.AuthMediator.Companion.RC_SIGN_IN
import com.rommac.mvp.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class AuthViewModel(private val authInteractor: AuthInteractor) : BaseViewModel() {
    private val _inProgress: MutableLiveData<Boolean> = MutableLiveData()
    val inProgress: LiveData<Boolean> = _inProgress
    private val _signIn: MutableLiveData<Event<Unit>> = MutableLiveData()
    val signIn: LiveData<Event<Unit>> = _signIn
    private val _signInSuccess: MutableLiveData<Event<Unit>> = MutableLiveData()
    val signInSuccess: LiveData<Event<Unit>> = _signInSuccess
    private val _signInError: MutableLiveData<Event<Unit>> = MutableLiveData()
    val signInError: LiveData<Event<Unit>> = _signInError
    override fun viewIsReady() {

    }

    fun onAuthClicked(){
        _inProgress.value = true
        fire(_signIn)
    }

    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == RC_SIGN_IN) {
            if (resultCode == Activity.RESULT_OK) {
                disposable(
                    authInteractor.signIn()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({
                            _inProgress.value = false
                            _signIn.value = event
                        }, {
                            _inProgress.value = false
                            _signInError.value = event
                        })
                )

            } else {
                _inProgress.value = false
                _signInError.value = event
            }
        }
    }
}