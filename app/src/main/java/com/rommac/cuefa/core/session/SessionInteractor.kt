package com.rommac.cuefa.core.session

import com.rommac.cuefa.core.GameSession
import io.reactivex.Observable

interface SessionInteractor {
    fun join(gameSession: GameSession): Observable<GameSession>
    fun getMy(): Observable<List<GameSession>>
    fun getNew(): Observable<List<GameSession>>
    fun create(): Observable<GameSession>
}