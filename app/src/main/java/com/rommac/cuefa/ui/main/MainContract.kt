package com.rommac.cuefa.ui.main

import android.content.Intent
import com.example.lib.mvp.MvpPresenter
import com.example.lib.mvp.MvpView

interface MainContract {
    interface CommonView{
        fun setVisibleProgressMain(isVisible:Boolean)
    }
    interface View: MvpView,CommonView{
        fun signin(email:String)
        fun signout()

        fun toProfile()
        fun toPlayers()
        fun toSessions()

        fun toAuth()

        fun showProgressBar()

        fun hideProgressBar()
    }

    interface Presenter: MvpPresenter<View> {

        fun onSignoutClicked()
        fun onAuthClicked()
        fun onBottomMenuItemClicked(pos: BOTTOM_NAV)
        fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?)
        fun onBackPressed()

        companion object{
            const val RC_SIGN_IN: Int = 1
        }
    }

    enum class BOTTOM_NAV{
        PLAYERS, SESSIONS
    }
}