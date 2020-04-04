package com.rommac.mvp

import com.rommac.mvp.MvpView
import com.rommac.mvp.CommonView

interface ChildView: MvpView {
    var commonView: CommonView
}