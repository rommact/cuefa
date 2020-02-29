package com.rommac.cuefa.core

fun GameSession.getOpponent(): Player?{
    return  players.firstOrNull() { !it.isOwner }
}
fun GameSession.getOwner(): Player?{
    return  players.firstOrNull() { it.isOwner }
}