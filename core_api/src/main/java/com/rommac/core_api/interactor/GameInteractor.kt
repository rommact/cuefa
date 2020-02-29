package com.rommac.core_api.interactor


import com.rommac.core_api.dto.GameSession
import com.rommac.core_api.network.dto.session.ACTION_TYPE
import io.reactivex.Observable

interface GameInteractor {
    fun selectAction(gameSession: GameSession, actionType: ACTION_TYPE): Observable<Boolean>
    fun exit(gameSession: GameSession): Observable<Boolean>
}