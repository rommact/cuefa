package com.rommac.mvp

import androidx.annotation.StringRes

interface MvpView{
    fun showError(@StringRes textId: Int)
}
