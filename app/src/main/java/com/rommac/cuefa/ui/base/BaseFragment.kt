package com.rommac.cuefa.ui.base

import android.app.Activity
import android.content.Context
import androidx.fragment.app.Fragment
import com.rommac.cuefa.ui.main.MainContract

open class BaseFragment : Fragment() {
    lateinit var commonView: MainContract.CommonView

    override fun onAttach(context: Context) {
        super.onAttach(context)
        commonView = context as MainContract.CommonView
    }
}