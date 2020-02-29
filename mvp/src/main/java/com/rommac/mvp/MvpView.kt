package com.example.lib.mvp

import androidx.annotation.StringRes
import com.rommac.cuefa.ui.main.MainContract

interface MvpView{
    fun showError(@StringRes textId: Int)
}
