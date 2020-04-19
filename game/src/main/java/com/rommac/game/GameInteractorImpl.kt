package com.rommac.game

import com.rommac.core_api.dto.GameSession
import com.rommac.core_api.dto.ACTION_TYPE
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
    ): Completable {
        return api.action(ActionBody(gameSession.id.toString(), actionType.ordinal))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun exit(gameSession: GameSession): Single<Boolean> {
        return api.exit(gameSession.id.toString())
            .map { t: Void -> true }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}