package com.rommac.sessions

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rommac.core_api.AuthDataProvider
import com.rommac.core_api.Event
import com.rommac.core_api.dto.GameSession
import com.rommac.core_api.dto.GameSessionData
import com.rommac.core_api.dto.STATUS
import com.rommac.core_api.dto.getOwner
import com.rommac.mvp.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SessionsViewModel
@Inject constructor(
    private val sessionInteractor: SessionInteractor,
    private val authDataProvider: AuthDataProvider
) :
     BaseViewModel() {

    private val _sessions: MutableLiveData<List<GameSession>> = MutableLiveData()
    val sessions: LiveData<List<GameSession>> = _sessions

    private val _toGame: MutableLiveData<Event<GameSessionData>> = MutableLiveData()
    val toGame: LiveData<Event<GameSessionData>> = _toGame

    private val _showJoinConfirm: MutableLiveData<Event<GameSession>> = MutableLiveData()
    val showJoinConfirm: LiveData<Event<GameSession>> = _showJoinConfirm

    private val _showConfirmSessionCreation: MutableLiveData<Event<Unit>> = MutableLiveData()
    val showConfirmSessionCreation: LiveData<Event<Unit>> = _showConfirmSessionCreation

    override fun viewIsReady() {
        disposable(sessionInteractor.getAll()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _sessions.value = it
            }, {
               //TODO
            })
        )
    }

    fun onItemClicked(gameSession: GameSession) {
        if(gameSession.status == STATUS.NEW
            && gameSession.getOwner()!!.uid != authDataProvider.authData.uid){
            _showJoinConfirm.value = Event(gameSession)
        }else{
            join(gameSession)
        }
    }

    private fun join(gameSession: GameSession){
        disposable(sessionInteractor.join(gameSession)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _toGame.value = Event(it)
            }, {
                //TODO
            })
        )
    }

     fun onJoinConfirmedClicked(gameSession: GameSession) {
        join(gameSession)
    }

     fun onAddSessionClicked() {
        _showConfirmSessionCreation.value = event
    }

     fun onCreationConfirmedClicked() {
        disposable(
            sessionInteractor.create()
                .flatMap { sessionInteractor.getNew() }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    _sessions.value = it
                }, {
                    //TODO
                })
        )
    }

     fun onCreationCanceledClicked() {
        //TODO
    }
}