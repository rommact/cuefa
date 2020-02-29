package com.rommac.cuefa.mvp

import com.example.lib.mvp.MvpView
import com.rommac.mvp.CommonView

interface FragmentView: MvpView {
    var commonView: CommonView
}