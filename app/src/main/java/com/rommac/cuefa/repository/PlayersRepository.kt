package com.rommac.cuefa.repository

import com.rommac.cuefa.core.Player
import io.reactivex.Flowable

interface PlayersRepository {
    fun getPlayers(query:String, limit:Int): Flowable<List<Player>>
}