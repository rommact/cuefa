package com.rommac.cuefa.ui.main

import android.content.Intent
import com.example.lib.mvp.MvpPresenter
import com.example.lib.mvp.MvpView

interface MainContract {
    interface View: MvpView{
        fun signin(email:String)
        fun signout()

        fun toProfile()
        fun toPlayers()
        fun toAuth()

        fun showProgressBar()
        fun hideProgressBar()
    }

    interface Presenter: MvpPresenter<View> {

        fun onSignoutClicked()
        fun onAuthClicked()
        fun onBottomMenuItemClicked(pos:Int)
        fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?)
        fun onBackPressed()
        companion object{
            const val RC_SIGN_IN: Int = 1
        }
    }
}