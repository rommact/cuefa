package com.rommac.game


import com.rommac.core_api.dto.GameSession
import com.rommac.core_api.dto.ACTION_TYPE
import io.reactivex.Single

interface GameInteractor {
    fun selectAction(gameSession: GameSession, actionType: ACTION_TYPE): Single<Boolean>
    fun exit(gameSession: GameSession): Single<Boolean>
}