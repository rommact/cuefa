package com.rommac.sessions

import com.rommac.mvp.BasePresenter
import com.rommac.core_api.dto.GameSession
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SessionsPresenter
@Inject constructor(private val sessionInteractor: SessionInteractor) :
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
            }))
    }

    override fun onItemClicked(gameSession: GameSession) {
        //TODO
    }

    override fun onAddSessionClicked() {
        view?.showConfirmSessionCreation()
    }

    override fun onCreationConfirmedClicked() {
        view?.commonView?.setVisibleProgressMain(true)
        disposable(
            sessionInteractor.create()
                .flatMap {  sessionInteractor.getNew()}
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