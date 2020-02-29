package com.rommac.core_impl.interactor

import com.rommac.core_api.dto.Player
import com.rommac.core_api.interactor.PlayerInteractor
import com.rommac.core_api.network.Api
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PlayerInteractorImpl @Inject constructor(private val api: Api) : PlayerInteractor {

    override fun getPlayers(query: String, limit: Int): Observable<List<Player>> {
        return api.getPlayers(query, limit)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

}