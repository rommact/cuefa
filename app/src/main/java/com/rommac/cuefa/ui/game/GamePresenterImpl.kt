package com.rommac.cuefa.ui.game

import com.example.lib.mvp.BasePresenter
import com.rommac.cuefa.R
import com.rommac.cuefa.interactor.GameInteractor
import com.rommac.cuefa.core.GameSession
import com.rommac.cuefa.core.getOpponent
import com.rommac.cuefa.network.dto.session.ACTION_TYPE
import javax.inject.Inject

class GamePresenterImpl@Inject constructor(private val gameInteractor: GameInteractor):
    GameContract.Presenter, BasePresenter<GameContract.View>() {

    private lateinit var mGameSession: GameSession
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
            it.setOpponentName(getOpponentName())
        }
    }

    private fun getOpponentName():String = mGameSession.getOpponent()!!.email


}