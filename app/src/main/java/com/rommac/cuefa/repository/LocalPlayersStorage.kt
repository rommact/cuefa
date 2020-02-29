package com.rommac.cuefa.repository

import com.rommac.cuefa.db.dto.PlayerDao
import com.rommac.cuefa.core.Player
import com.rommac.cuefa.di.scope.FragmentScope
import io.reactivex.Flowable
import io.reactivex.Observable
import javax.inject.Inject

class LocalPlayersStorage
@Inject constructor(private val playerDao: PlayerDao) :
    PlayersRepository {
    override fun getPlayers(query: String, limit: Int): Observable<List<Player>> {
        return playerDao.get(query, limit)
            .map {
                it.map { playerItem ->
                    Player(playerItem.uid, playerItem.email, playerItem.isFriend, playerItem.isMy)
                }
            }

    }

}