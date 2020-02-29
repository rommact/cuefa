package com.example.lib.mvp

import androidx.annotation.StringRes
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ViewModel
import com.rommac.cuefa.R
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable


abstract class BasePresenter<V : MvpView> : ViewModel(), MvpPresenter<V>, LifecycleObserver {

    override val TAG: String = this::class.qualifiedName ?: "Unknown Presenter"
    private var viewLifecycle: Lifecycle? = null
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
    protected var view: V? = null
        private set

    protected val isViewAttached: Boolean
        get() = view != null

    override fun attachView(mvpView: V, viewLifecycle: Lifecycle) {
        view = mvpView
        this.viewLifecycle = viewLifecycle
    }


    override fun detachView() {
        view = null
        viewLifecycle = null
    }

    protected fun disposable(disposable: Disposable):Disposable{
        compositeDisposable.add(disposable)
        return disposable
    }

    protected fun showError(@StringRes textId: Int){
        view?.showError(textId)
    }

    protected fun showError(){
        showError(R.string.any_error)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    override fun destroy() {
        detachView()
        compositeDisposable.dispose()
        compositeDisposable.clear()
    }


}
