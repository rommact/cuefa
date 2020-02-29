package com.rommac.core_api.dto

import com.rommac.core_api.dto.GameSession
import com.rommac.core_api.dto.Player

fun GameSession.getOpponent(): Player?{
    return  players.firstOrNull() { !it.isOwner }
}
fun GameSession.getOwner(): Player?{
    return  players.firstOrNull() { it.isOwner }
}