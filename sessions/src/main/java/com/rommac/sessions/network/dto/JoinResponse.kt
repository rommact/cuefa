package com.rommac.sessions.network.dto

import com.rommac.network_api.dto.GameSessionItem
import com.rommac.network_api.dto.GameSessionStateItem

data class JoinResponse(
    val gameSession: GameSessionItem,
    val gameSessionState: GameSessionStateItem
)