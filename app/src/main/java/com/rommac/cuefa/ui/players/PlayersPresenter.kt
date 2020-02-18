package com.rommac.cuefa.ui.players

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.lib.mvp.BasePresenter
import com.rommac.cuefa.core.Player
import com.rommac.cuefa.repository.PlayersRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PlayersPresenter
@Inject constructor(private val playersRepository: PlayersRepository) :
    PlayersContract.Presenter, BasePresenter<PlayersContract.View>() {

    private val playersData: MutableLiveData<List<Player>> = MutableLiveData()

    private val limit = 10
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
    override fun onQueryTextChanged(text: String) {
        if(text.isEmpty()) {
            playersData.postValue(ArrayList())
            return
        }
        val subscribe = playersRepository.getPlayers(text, limit)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                playersData.postValue(it)
            }, {

            })
        compositeDisposable.add(subscribe)

    }

    override fun onItemClicked(player: Player) {
        //TODO
    }

    override fun getPlayersLiveData(): LiveData<List<Player>> {
        return playersData
    }

    override fun viewIsReady() {

    }

    override fun destroy() {
        super.destroy()
        compositeDisposable.dispose()
    }
}