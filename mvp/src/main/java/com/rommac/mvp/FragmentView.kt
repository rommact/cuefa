package com.rommac.mvp

import com.rommac.mvp.MvpView
import com.rommac.mvp.CommonView

interface FragmentView: MvpView {
    var commonView: CommonView
}