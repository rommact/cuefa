package com.rommac.cuefa.ui.session

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.lib.mvp.BasePresenter
import com.rommac.cuefa.core.GameSession
import com.rommac.cuefa.core.Player
import com.rommac.cuefa.core.session.SessionInteractor
import com.rommac.cuefa.repository.PlayersRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SessionsPresenter
@Inject constructor(private val sessionInteractor: SessionInteractor) :
    SessionsContract.Presenter, BasePresenter<SessionsContract.View>() {

    private val sessionsData: MutableLiveData<List<GameSession>> = MutableLiveData()


    override fun onItemClicked(gameSession: GameSession) {
    }

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