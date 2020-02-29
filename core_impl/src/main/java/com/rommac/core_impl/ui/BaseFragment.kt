package com.rommac.core_impl.ui

import android.app.Activity
import android.content.Context
import androidx.fragment.app.Fragment
import com.rommac.core_api.mvp.CommonView

open class BaseFragment : Fragment() {
    lateinit var commonView: CommonView

    override fun onAttach(context: Context) {
        super.onAttach(context)
        commonView = context as CommonView
    }
}