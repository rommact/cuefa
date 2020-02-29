package com.rommac.mvp

import androidx.lifecycle.Lifecycle


interface MvpPresenter<V : MvpView> {

    val TAG: String

    fun attachView(mvpView: V, viewLifecycle: Lifecycle)

    fun viewIsReady()

    fun detachView()

    fun destroy()

}
