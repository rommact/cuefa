package com.rommac.core_api.mediator

import android.content.Context
import com.rommac.core_api.dto.GameSession
import com.rommac.core_api.dto.GameSessionState

interface GameMediator {
    fun toGame(
        //todo refact
        gameSession: GameSession,
        state: GameSessionState
    )
}