package com.rommac.cuefa.mvp

import android.content.Context
import android.widget.Toast
import com.example.lib.mvp.MvpView
import com.rommac.cuefa.ui.base.BaseFragment
import com.rommac.cuefa.ui.main.MainContract

abstract class BaseView(private val context: Context) : MvpView {



    override fun showError(textId: Int) {
        Toast.makeText(context, textId, Toast.LENGTH_SHORT).show()
    }

}