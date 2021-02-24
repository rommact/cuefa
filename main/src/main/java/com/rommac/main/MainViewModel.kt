package com.rommac.main

import android.app.Activity
import android.content.Intent
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rommac.core_api.AuthDataProvider
import com.rommac.core_api.Event
import com.rommac.mvp.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainViewModel
@Inject constructor(
    private val authDataProvider: AuthDataProvider
) : BaseViewModel() {

    private val _toSession: MutableLiveData<Event<Unit>> = MutableLiveData()
    val toSession: LiveData<Event<Unit>> = _toSession

    private val _toPlayers: MutableLiveData<Event<Unit>> = MutableLiveData()
    val toPlayers: LiveData<Event<Unit>> = _toPlayers

    private val _toAuth: MutableLiveData<Event<Unit>> = MutableLiveData()
    val toAuth: LiveData<Event<Unit>> = _toAuth

    private val _inProgress: MutableLiveData<Boolean> = MutableLiveData()
    val inProgress: LiveData<Boolean> = _inProgress

    private val _signIn: MutableLiveData<Event<String>> = MutableLiveData()
    val signIn: LiveData<Event<String>> = _signIn

    private val _signOut: MutableLiveData<Event<Unit>> = MutableLiveData()
    val signOut: LiveData<Event<Unit>> = _signOut

    fun onBottomMenuItemClicked(pos: MainContract.BOTTOM_NAV) {
        when (pos) {
            MainContract.BOTTOM_NAV.SESSIONS -> _toSession.postValue(event)
            MainContract.BOTTOM_NAV.PLAYERS -> _toPlayers.postValue(event)
        }
    }



    override fun viewIsReady() {
        if (authDataProvider.authData.status) {
            _signIn.value = Event(authDataProvider.authData.email)
        } else {
            _signOut.value = event
            _toAuth.value = event
        }
    }

    fun onSignoutClicked() {
        _signOut.value = event
    }

    fun onAuthClicked() {
        _signOut.value = event
    }

    fun onBackPressed() {

    }


    companion object {
        val RC_SIGN_IN: Int = 1
    }
}