package com.rommac.cuefa.repository

import com.rommac.cuefa.core.Player
import io.reactivex.Flowable
import io.reactivex.Observable

interface PlayersRepository {
    fun getPlayers(query:String, limit:Int): Observable<List<Player>>
}