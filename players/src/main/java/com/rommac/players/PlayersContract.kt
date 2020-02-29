package com.rommac.players

import androidx.lifecycle.LiveData
import com.rommac.mvp.MvpPresenter
import com.rommac.mvp.MvpView
import com.rommac.core_api.dto.Player

interface PlayersContract {
    interface View: MvpView {

    }
    interface Presenter: MvpPresenter<View> {
        fun onQueryTextChanged(text:String)
        fun onItemClicked(player: Player)
        fun getPlayersLiveData():LiveData<List<Player>>
    }
}