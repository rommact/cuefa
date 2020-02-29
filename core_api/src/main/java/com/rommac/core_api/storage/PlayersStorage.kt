package com.rommac.core_api.storage

import com.rommac.core_api.dto.Player
import io.reactivex.Flowable
import io.reactivex.Observable

interface PlayersStorage {
    fun getPlayers(query:String, limit:Int): Observable<List<Player>>
}