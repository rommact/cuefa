package com.rommac.mvp

import android.content.Context
import android.widget.Toast

abstract class BaseView(private val context: Context) : MvpView {



    override fun showError(textId: Int) {
        Toast.makeText(context, textId, Toast.LENGTH_SHORT).show()
    }

}