package com.rommac.mvp

import android.app.Activity
import android.content.Context
import androidx.fragment.app.Fragment

open class BaseFragment : Fragment() {
    lateinit var commonView: CommonView

    override fun onAttach(context: Context) {
        super.onAttach(context)
        commonView = context as CommonView
    }
}