package com.rommac.players

import com.rommac.core_api.dto.Player
import com.rommac.network_api.dto.toPlayers
import com.rommac.players.network.PlayersApi
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PlayerInteractorImpl @Inject constructor(private val api: PlayersApi) :
    PlayerInteractor {

    override fun getPlayers(query: String, limit: Int): Single<List<Player>> {
        return api.getPlayers(query, limit)
            .map { it.toPlayers() }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

}