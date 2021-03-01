package com.rommac.network_api.dto

import com.rommac.core_api.dto.GameSession
import com.rommac.core_api.dto.Player
import com.rommac.core_api.dto.STATUS


fun GameSessionItem.toGameSession(): GameSession =
    GameSession(
        id,
        players.map {
            it.toPlayer()
        },
        timeStart, STATUS.values()[status]
    )


fun GameSession.toGameSessionItem(): GameSessionItem =
    GameSessionItem(
        id,
        players.toPlayerItems(),
        timeStart,
        status.ordinal
    )


fun List<Player>.toPlayerItems(): List<PlayerItem> = map { it.toPlayerItem() }

fun List<PlayerItem>.toPlayers(): List<Player> = map { it.toPlayer() }

fun Player.toPlayerItem(): PlayerItem =
    PlayerItem(
        this.uid,
        this.email,
        this.isFriend,
        this.isOwner
    )


fun PlayerItem.toPlayer(): Player =
    Player(this.uid, this.email, this.isFriend, this.isOwner)
