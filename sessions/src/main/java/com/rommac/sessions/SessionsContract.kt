package com.rommac.sessions

import com.rommac.mvp.MvpPresenter
import com.rommac.core_api.dto.GameSession
import com.rommac.mvp.ChildView

interface SessionsContract {
    interface View : ChildView {
        fun showConfirmSessionCreation()
        fun setSessions(it: List<GameSession>)
    }

    interface Presenter : MvpPresenter<View> {
        fun onItemClicked(gameSession: GameSession)
        fun onAddSessionClicked()
        fun onCreationConfirmedClicked()
        fun onCreationCanceledClicked()
    }
}