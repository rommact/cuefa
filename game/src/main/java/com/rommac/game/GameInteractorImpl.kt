package com.rommac.game

import com.rommac.core_api.dto.GameSession
import com.rommac.core_api.dto.ACTION_TYPE
import com.rommac.core_api.dto.GameSessionState
import com.rommac.game.network.ActionBody
import com.rommac.game.network.GameApi
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class GameInteractorImpl @Inject constructor(private val api: GameApi):
    GameInteractor {
    override fun selectAction(
        gameSession: GameSession,
        actionType: ACTION_TYPE
    ): Single<GameSessionState> {
        return api.action(ActionBody(gameSession.id.toString(), actionType.ordinal))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun exit(gameSession: GameSession): Completable {
        return api.exit(gameSession.id.toString())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}