package com.rommac.players

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rommac.core_api.Event
import com.rommac.mvp.BaseViewModel
import com.rommac.core_api.dto.Player
import com.rommac.core_api.storage.PlayersStorage
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PlayersViewModel
@Inject constructor(private val playersRepository: PlayersStorage) :
     BaseViewModel() {

    private val _players: MutableLiveData<List<Player>> = MutableLiveData()
    val players: LiveData<List<Player>> = _players
    private val limit = 10
     fun onQueryTextChanged(text: String) {
        if(text.isEmpty()) {
            _players.value = listOf()
            return
        }
        val subscribe = playersRepository.getPlayers(text, limit)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _players.value = it
            }, {
                //TODO
            })
        disposable(subscribe)

    }

     fun onItemClicked(player: Player) {
        //TODO
    }

    override fun viewIsReady() {
        //TODO
    }

}