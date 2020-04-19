package com.rommac.sessions

import com.rommac.core_api.AuthDataProvider
import com.rommac.core_api.dto.GameSession
import com.rommac.core_api.dto.STATUS
import com.rommac.core_api.dto.getOwner
import com.rommac.mvp.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SessionsPresenter
@Inject constructor(
    private val sessionInteractor: SessionInteractor,
    private val authDataProvider: AuthDataProvider
) :
    SessionsContract.Presenter, BasePresenter<SessionsContract.View>() {


    override fun viewIsReady() {
        view?.commonView?.setVisibleProgressMain(true)
        disposable(sessionInteractor.getNew()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                val sessions = it
                view?.let {
                    view?.setSessions(sessions)
                    view?.commonView?.setVisibleProgressMain(false)
                }
            }, {
                view?.commonView?.setVisibleProgressMain(false)
                showError()
            })
        )
    }

    override fun onItemClicked(gameSession: GameSession) {
        if(gameSession.status == STATUS.NEW
            && gameSession.getOwner()!!.uid != authDataProvider.authData.uid){
            view?.showJoinConfirm(gameSession)
        }else{
            join(gameSession)
        }
    }

    private fun join(gameSession: GameSession){
        view?.commonView?.setVisibleProgressMain(true)
        disposable(sessionInteractor.join(gameSession)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                view?.toGame(it.gameSession,it.gameSessionState)
                view?.commonView?.setVisibleProgressMain(false)
            }, {
                view?.commonView?.setVisibleProgressMain(false)
            })
        )
    }

    override fun onJoinConfirmedClicked(gameSession: GameSession) {
        join(gameSession)
    }

    override fun onAddSessionClicked() {
        view?.showConfirmSessionCreation()
    }

    override fun onCreationConfirmedClicked() {
        view?.commonView?.setVisibleProgressMain(true)
        disposable(
            sessionInteractor.create()
                .flatMap { sessionInteractor.getNew() }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    view?.setSessions(it)
                    view?.commonView?.setVisibleProgressMain(false)
                }, {
                    view?.commonView?.setVisibleProgressMain(false)
                    showError()
                })
        )
    }

    override fun onCreationCanceledClicked() {
        //TODO
    }
}