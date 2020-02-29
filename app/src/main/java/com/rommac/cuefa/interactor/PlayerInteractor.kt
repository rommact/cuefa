package com.rommac.cuefa.interactor

import com.rommac.cuefa.core.Player
import com.rommac.cuefa.network.Api
import com.rommac.cuefa.repository.PlayersRepository
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PlayerInteractor @Inject constructor(private val api:Api): PlayersRepository {

    override fun getPlayers(query: String, limit: Int): Observable<List<Player>> {
        return api.getPlayers(query, limit)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

}