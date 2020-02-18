package com.rommac.cuefa.ui.players

import androidx.lifecycle.LiveData
import com.example.lib.mvp.MvpPresenter
import com.example.lib.mvp.MvpView
import com.rommac.cuefa.core.Player

interface PlayersContract {
    interface View:MvpView{

    }
    interface Presenter:MvpPresenter<View>{
        fun onQueryTextChanged(text:String)
        fun onItemClicked(player: Player)
        fun getPlayersLiveData():LiveData<List<Player>>
    }
}