package com.rommac.sessions


import com.rommac.core_api.dto.*
import com.rommac.network_api.dto.toGameSession
import com.rommac.network_api.dto.toGameSessionItem
import com.rommac.sessions.network.SessionsApi
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SessionInteractorImpl @Inject constructor(private val api: SessionsApi) :
    SessionInteractor {

    override fun join(gameSession: GameSession): Single<GameSessionData> {
        return api.join(gameSession.toGameSessionItem())
            .map { GameSessionData(it.gameSession.toGameSession(), GameSessionState(it.gameSessionState.actions,it.gameSessionState.winnerUid)) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getMy(): Single<List<GameSession>> {
        return api.getSessions(my = true)
            .map {
                it.map { gameSessionItem ->
                    gameSessionItem.toGameSession()
                }
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getAll(): Single<List<GameSession>> {
        return api.getSessions()
            .map {
                it.map { gameSessionItem ->
                    gameSessionItem.toGameSession()
                }
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getActive(): Single<List<GameSession>> {
        return api.getActiveSessions()
            .map {
                it.map { gameSessionItem ->
                    gameSessionItem.toGameSession()
                }
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
    override fun getNew(): Single<List<GameSession>> {
        return api.getSessions(STATUS.NEW.ordinal)
            .map {
                it.map { gameSessionItem ->
                    gameSessionItem.toGameSession()
                }
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
    override fun create(): Single<GameSession> {
        return api.createSession()
            .map {
                it.toGameSession()
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getState(gameSession: GameSession): Single<GameSessionState> {
        return api.state()
            .map { GameSessionState(it.actions, it.winnerUid) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}

