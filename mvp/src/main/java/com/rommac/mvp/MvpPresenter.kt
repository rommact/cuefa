package com.rommac.mvp

import androidx.lifecycle.Lifecycle


interface MvpPresenter<V : MvpView> {

    val TAG: String

    fun destroy()

}
