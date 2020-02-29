package com.rommac.core_api.interactor

import com.rommac.core_api.dto.GameSession
import io.reactivex.Observable

interface SessionInteractor {
    fun join(gameSession: GameSession): Observable<GameSession>
    fun getMy(): Observable<List<GameSession>>
    fun getNew(): Observable<List<GameSession>>
    fun create(): Observable<GameSession>
}