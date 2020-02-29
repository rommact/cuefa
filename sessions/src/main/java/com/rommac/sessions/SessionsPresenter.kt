package com.rommac.sessions

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rommac.mvp.BasePresenter
import com.rommac.core_api.dto.GameSession
import com.rommac.core_api.interactor.SessionInteractor
import com.rommac.sessions.SessionsContract
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SessionsPresenter
@Inject constructor(private val sessionInteractor: SessionInteractor) :
    SessionsContract.Presenter, BasePresenter<SessionsContract.View>() {

    private val sessionsData: MutableLiveData<List<GameSession>> = MutableLiveData()


    override fun getSessionsLiveData(): LiveData<List<GameSession>> {
        return sessionsData
    }

    override fun viewIsReady() {
        view?.commonView?.setVisibleProgressMain(true)
        disposable(
            sessionInteractor.getNew()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                sessionsData.postValue(it)
                view?.commonView?.setVisibleProgressMain(false)
            }, {
                view?.commonView?.setVisibleProgressMain(false)
                showError()
            })
        )
    }

    override fun onItemClicked(gameSession: GameSession) {

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
                    sessionsData.postValue(it)
                    view?.commonView?.setVisibleProgressMain(false)
                }, {
                    view?.commonView?.setVisibleProgressMain(false)
                    showError()
                })
        )
    }

    override fun onCreationCanceledClicked() {


    }
}