package com.rommac.main

import android.content.Intent
import com.rommac.mvp.MvpPresenter
import com.rommac.mvp.MvpView
import com.rommac.mvp.CommonView

interface MainContract {
    enum class BOTTOM_NAV{
        PLAYERS, SESSIONS
    }
}