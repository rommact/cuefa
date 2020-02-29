package com.rommac.cuefa.ui.game

import com.example.lib.mvp.MvpPresenter
import com.example.lib.mvp.MvpView
import com.rommac.cuefa.core.GameSession
import com.rommac.cuefa.network.dto.session.ACTION_TYPE

interface GameContract {
    interface View:MvpView{
        fun setSelectedActionMy(actionType: ACTION_TYPE)
        fun setSelectedActionOpponent(actionType: ACTION_TYPE)
        fun setOpponentName(name: String)
        fun setActionsList(actions: List<ACTION_TYPE>)
        fun exit()
        fun setMyProgressVisibility(visible: Boolean)
        fun setOpponentProgressVisibility(visible: Boolean)
        fun setEnabledUI(enabled: Boolean)
    }

    interface Presenter: MvpPresenter<View>{
        var gameSession: GameSession
        fun onActionClicked(actionType: ACTION_TYPE)
        fun onExitClicked()
    }
}