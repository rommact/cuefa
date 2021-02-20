package com.rommac.game

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rommac.core_api.dto.*
import com.rommac.mvp.BaseViewModel
import javax.inject.Inject

class GameViewModelImpl @Inject constructor(
    private val gameInteractor: GameInteractor,
    private val authData: AuthData
) : BaseViewModel() {

    private lateinit var mGameSession: GameSession
    private lateinit var mGameSessionState: GameSessionState

    var gameSessionState: GameSessionState
        get() = mGameSessionState
        set(value) {
            mGameSessionState = value
        }
    var gameSession: GameSession
        get() = mGameSession
        set(value) {
            mGameSession = value
        }


    private val _myAction: MutableLiveData<ACTION_TYPE> = MutableLiveData()
    private val _opponentAction: MutableLiveData<ACTION_TYPE> = MutableLiveData()
    private val _playerInProgress: MutableLiveData<Boolean> = MutableLiveData()
    private val _opponentInProgress: MutableLiveData<Boolean> = MutableLiveData()
    private val _uiIsEnabled: MutableLiveData<Boolean> = MutableLiveData()
    private val _actionList: MutableLiveData<List<ACTION_TYPE>> = MutableLiveData()
    private val _opponentName: MutableLiveData<String> = MutableLiveData()

     val myAction: LiveData<ACTION_TYPE> = _myAction
     val opponentAction: LiveData<ACTION_TYPE> = _opponentAction
     val playerInProgress: LiveData<Boolean> = _playerInProgress
     val opponentInProgress: LiveData<Boolean> = _opponentInProgress
     val uiIsEnabled: LiveData<Boolean> =_uiIsEnabled
     val actionList: LiveData<List<ACTION_TYPE>> = _actionList
     val opponentName: LiveData<String> = _opponentName


    fun onActionClicked(actionType: ACTION_TYPE) {
        playerActionProgress(true)
        disposable(
            gameInteractor.selectAction(mGameSession, actionType)
                .subscribe({ sessionState ->
                    if (sessionState!!.actions.size == 2) {
                        val actionOpponent = sessionState.actions.find { it.uid != authData.uid }
                        _opponentAction.postValue(ACTION_TYPE.values()[actionOpponent!!.action])
                    }
                    _playerInProgress.postValue(false)
                    _myAction.postValue(actionType)
                }, {
                    Log.e("", "", it)
                    playerActionProgress(false)
//                    showError(R.string.any_error)
                })
        )
    }

    private fun playerActionProgress(isStarted: Boolean) {
        _playerInProgress.postValue(isStarted)
        _uiIsEnabled.postValue(!isStarted)
    }

    fun onExitClicked() {
//        disposable(
//            gameInteractor.exit(mGameSession)
//                .subscribe({
//                    view?.exit()
//                }, {
//                    showError(R.string.any_error)
//                })
//        )
    }

    override fun onCleared() {
        super.onCleared()
        gameInteractor.exit(mGameSession)
            .subscribe({

            }, {
                it.printStackTrace()
            })
    }

    override fun viewIsReady() {
        _actionList.postValue(listOf(ACTION_TYPE.SCISSORS, ACTION_TYPE.STONE, ACTION_TYPE.PAPER))
        if (mGameSession.getOpponent() != null) {
            _opponentName.postValue(mGameSession.getOpponent()!!.email)
        } else {
            _opponentName.postValue("")
        }

        val actionMy = mGameSessionState.actions.find { it.uid == authData.uid }
        val actionOpponent = mGameSessionState.actions.find { it.uid != authData.uid }
        if (actionMy != null) {
            _myAction.postValue(ACTION_TYPE.values()[actionMy.action])
            _uiIsEnabled.postValue(false)
        } else {
            _playerInProgress.postValue(true)
        }
        if (actionOpponent != null) {
            _opponentAction.postValue(ACTION_TYPE.values()[actionOpponent.action])
            _playerInProgress.postValue(false)
        } else {
            _playerInProgress.postValue(true)
        }
    }


}