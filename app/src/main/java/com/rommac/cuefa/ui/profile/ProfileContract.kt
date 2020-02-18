package com.rommac.cuefa.ui.profile

import com.example.lib.mvp.MvpPresenter
import com.example.lib.mvp.MvpView

interface ProfileContract {
    interface ProfileView: MvpView {

    }

    interface ProfilePresenterI:MvpPresenter<ProfileView>{

    }
}