package com.rommac.cuefa.mvp

import com.example.lib.mvp.MvpView
import com.rommac.cuefa.ui.main.MainContract

interface FragmentView: MvpView {
    var commonView: MainContract.CommonView
}