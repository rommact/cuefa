package com.rommac.core_impl.storage


import com.rommac.core_api.database.PlayerDao
import com.rommac.core_api.dto.Player
import com.rommac.core_api.storage.PlayersStorage
import io.reactivex.Observable
import javax.inject.Inject

class LocalPlayersStorage
@Inject constructor(private val playerDao: PlayerDao) :
    PlayersStorage {
    override fun getPlayers(query: String, limit: Int): Observable<List<Player>> {
        return playerDao.get(query, limit)
            .map {
                it.map { playerItem ->
                    Player(playerItem.uid, playerItem.email, playerItem.isFriend, playerItem.isOwner)
                }
            }

    }

}