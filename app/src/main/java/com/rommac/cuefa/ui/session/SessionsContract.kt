package com.rommac.cuefa.ui.session

import androidx.lifecycle.LiveData
import com.example.lib.mvp.MvpPresenter
import com.example.lib.mvp.MvpView
import com.rommac.cuefa.core.GameSession
import com.rommac.cuefa.core.Player
import com.rommac.cuefa.mvp.FragmentView

interface SessionsContract {
    interface View: FragmentView {
        fun showConfirmSessionCreation()
    }
    interface Presenter:MvpPresenter<View>{
        fun onItemClicked(gameSession: GameSession)
        fun onAddSessionClicked()
        fun getSessionsLiveData():LiveData<List<GameSession>>
        fun onCreationConfirmedClicked()
        fun onCreationCanceledClicked()
    }
}