package com.example.lib.mvp

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ViewModel


abstract class BasePresenter<V : MvpView> : ViewModel(), MvpPresenter<V>, LifecycleObserver {

    override val TAG: String = this::class.qualifiedName ?: "Unknown Presenter"
    private var viewLifecycle: Lifecycle? = null

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

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    override fun destroy() {
        detachView()
    }


}
