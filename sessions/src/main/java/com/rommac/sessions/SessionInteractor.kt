package com.rommac.sessions

import com.rommac.core_api.dto.GameSession
import com.rommac.core_api.dto.GameSessionData
import com.rommac.core_api.dto.GameSessionState
import io.reactivex.Observable
import io.reactivex.Single

interface SessionInteractor {
    fun join(gameSession: GameSession): Single<GameSessionData>
    fun getMy(): Single<List<GameSession>>
    fun getNew(): Single<List<GameSession>>
    fun create(): Single<GameSession>
    fun getState(gameSession: GameSession): Single<GameSessionState>
}