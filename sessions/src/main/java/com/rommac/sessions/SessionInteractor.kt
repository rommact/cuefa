package com.rommac.sessions

import com.rommac.core_api.dto.GameSession
import io.reactivex.Observable
import io.reactivex.Single

interface SessionInteractor {
    fun join(gameSession: GameSession): Single<GameSession>
    fun getMy(): Single<List<GameSession>>
    fun getNew(): Single<List<GameSession>>
    fun create(): Single<GameSession>
}