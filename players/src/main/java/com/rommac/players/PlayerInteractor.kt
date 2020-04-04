package com.rommac.players

import com.rommac.core_api.dto.Player
import com.rommac.network_api.dto.PlayerItem
import io.reactivex.Single

interface PlayerInteractor {
    fun getPlayers(query: String, limit: Int): Single<List<Player>>
}