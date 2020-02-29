package com.rommac.core_impl.interactor

import com.rommac.core_api.dto.GameSession
import com.rommac.core_api.interactor.GameInteractor
import com.rommac.core_api.network.Api
import com.rommac.cuefa.network.dto.session.ACTION_TYPE
import com.rommac.cuefa.network.dto.session.ActionBody
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class GameInteractorImpl @Inject constructor(private val api: Api):
    GameInteractor {
    override fun selectAction(
        gameSession: GameSession,
        actionType: ACTION_TYPE
    ): Observable<Boolean> {
        return api.action(ActionBody(gameSession.id.toString(), actionType))
            .map { t: Void -> true }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun exit(gameSession: GameSession): Observable<Boolean> {
        return api.exit(gameSession.id.toString())
            .map { t: Void -> true }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}