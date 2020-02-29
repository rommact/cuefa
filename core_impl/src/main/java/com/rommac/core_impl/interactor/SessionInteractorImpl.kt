package com.rommac.core_impl.interactor


import com.rommac.core_api.dto.GameSession
import com.rommac.core_api.dto.Player
import com.rommac.core_api.dto.STATUS
import com.rommac.core_api.interactor.SessionInteractor
import com.rommac.core_api.network.Api
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SessionInteractorImpl @Inject constructor( private val api: Api): SessionInteractor {

    override fun join(gameSession: GameSession): Observable<GameSession> {
        return api.join(gameSession)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getMy(): Observable<List<GameSession>> {
        return api.getSessions(my = true)
            .map {
                it.map { gameSessionItem ->
                    GameSession(gameSessionItem.id,
                        gameSessionItem.players.map {
                            Player(it.uid, it.email, it.isFriend, it.isOwner)
                        },
                       gameSessionItem.timeStart, STATUS.values()[gameSessionItem.status])
                }
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getNew(): Observable<List<GameSession>> {
        return api.getSessions(STATUS.NEW.ordinal)
            .map {
                it.map { gameSessionItem ->
                    GameSession(gameSessionItem.id,
                        gameSessionItem.players.map {
                            Player(it.uid, it.email, it.isFriend, it.isOwner)
                        },
                        gameSessionItem.timeStart,STATUS.values()[gameSessionItem.status])
                }
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun create(): Observable<GameSession> {
        return api.createSession()
            .map {
                GameSession(it.id,
                    it.players.map {
                        Player(it.uid, it.email, it.isFriend, it.isOwner)
                    },
                    it.timeStart,STATUS.values()[it.status])
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}