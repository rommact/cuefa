package com.rommac.players

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rommac.mvp.BasePresenter
import com.rommac.core_api.dto.Player
import com.rommac.core_api.storage.PlayersStorage
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PlayersPresenter
@Inject constructor(private val playersRepository: PlayersStorage) :
    PlayersContract.Presenter, BasePresenter<PlayersContract.View>() {


    private val limit = 10
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
    override fun onQueryTextChanged(text: String) {
        if(text.isEmpty()) {
            view?.setPlayers(ArrayList())
            return
        }
        val subscribe = playersRepository.getPlayers(text, limit)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                view?.setPlayers(it)
            }, {

            })
        compositeDisposable.add(subscribe)

    }

    override fun onItemClicked(player: Player) {
        //TODO
    }

    override fun viewIsReady() {
        //TODO
    }

    override fun destroy() {
        super.destroy()
        compositeDisposable.dispose()
    }
}