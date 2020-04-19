package com.rommac.game

import android.util.Log
import com.rommac.core_api.AuthDataProvider
import com.rommac.core_api.dto.*
import com.rommac.mvp.BasePresenter
import javax.inject.Inject

class GamePresenterImpl @Inject constructor(private val gameInteractor: GameInteractor, private val authData: AuthData):
    GameContract.Presenter, BasePresenter<GameContract.View>() {

    private lateinit var mGameSession: GameSession
    private lateinit var mGameSessionState: GameSessionState

    override var gameSessionState: GameSessionState
        get() = mGameSessionState
        set(value) {mGameSessionState = value}
    override var gameSession: GameSession
        get() = mGameSession
        set(value) {mGameSession = value}


    override fun onActionClicked(actionType: ACTION_TYPE) {
        myActionProgress(true)
        disposable(
            gameInteractor.selectAction(mGameSession,actionType)
            .subscribe( {
                view?.setMyProgressVisibility(false)
                view?.setSelectedActionMy(actionType)
            },  {
                Log.e("","",it)
                myActionProgress(false)
                showError(R.string.any_error)
            })
        )
    }

    private fun myActionProgress(isStarted: Boolean){
        view?.setMyProgressVisibility(isStarted)
        view?.setEnabledUI(!isStarted)
    }

    override fun onExitClicked() {
        disposable(
            gameInteractor.exit(mGameSession)
                .subscribe( {
                    view?.exit()
                },  {
                    showError(R.string.any_error)
                })
        )
    }

    override fun viewIsReady() {

        view?.let {
            it.setActionsList(listOf(ACTION_TYPE.SCISSORS, ACTION_TYPE.STONE, ACTION_TYPE.PAPER))
            if(mGameSession.getOpponent() != null){
                it.setOpponentName(mGameSession.getOpponent()!!.email)
            }else{
                it.setOpponentName("")
            }

            val actionMy = mGameSessionState.actions.find {it.uid ==  authData.uid}
            val actionOpponent = mGameSessionState.actions.find {it.uid !=  authData.uid}
            if(actionMy != null){
                view?.setSelectedActionMy(ACTION_TYPE.values()[actionMy.action])
            }
            if(actionOpponent != null){
                view?.setSelectedActionOpponent(ACTION_TYPE.values()[actionOpponent.action])
            }
        }
    }



}