package com.rommac.game


import com.rommac.core_api.dto.GameSession
import com.rommac.core_api.dto.ACTION_TYPE
import com.rommac.core_api.dto.GameSessionState
import io.reactivex.Completable
import io.reactivex.Single

interface GameInteractor {
    fun selectAction(gameSession: GameSession, actionType: ACTION_TYPE): Single<GameSessionState>
    fun exit(gameSession: GameSession): Completable
}