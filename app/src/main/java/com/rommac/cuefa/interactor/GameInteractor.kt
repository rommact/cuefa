package com.rommac.cuefa.interactor

import com.rommac.cuefa.core.GameSession
import com.rommac.cuefa.network.dto.session.ACTION_TYPE
import io.reactivex.Observable

interface GameInteractor {
    fun selectAction(gameSession: GameSession, actionType: ACTION_TYPE): Observable<Boolean>
    fun exit(gameSession: GameSession): Observable<Boolean>
}