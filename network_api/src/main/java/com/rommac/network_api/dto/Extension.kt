package com.rommac.network_api.dto

import com.rommac.core_api.dto.GameSession
import com.rommac.core_api.dto.Player
import com.rommac.core_api.dto.STATUS


fun GameSessionItem.toGameSession(): GameSession {
    return  GameSession(
        this.id,
        this.players.map {
            Player(it.uid, it.email, it.isFriend, it.isOwner)
        },
        this.timeStart, STATUS.values()[this.status]
    )
}

fun GameSession.toGameSessionItem(): GameSessionItem {
    return GameSessionItem(
        this.id,
        this.players.toPlayerItems(),
        this.timeStart,
        this.status.ordinal
    );
}


fun List<Player>.toPlayerItems(): List<PlayerItem>{
    return this.map {it.toPlayerItem()}
}
fun List<PlayerItem>.toPlayers(): List<Player>{
    return this.map {it.toPlayer()}
}
fun Player.toPlayerItem(): PlayerItem {
    return PlayerItem(
        this.uid,
        this.email,
        this.isFriend,
        this.isOwner
    )
}

fun PlayerItem.toPlayer(): Player{
    return Player(this.uid, this.email, this.isFriend, this.isOwner)
}