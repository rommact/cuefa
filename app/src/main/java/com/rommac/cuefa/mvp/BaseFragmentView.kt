package com.rommac.cuefa.mvp

import android.content.Context
import com.rommac.cuefa.ui.base.BaseFragment
import com.rommac.cuefa.ui.main.MainContract

open class BaseFragmentView(context: BaseFragment) : BaseView(context.context!!), FragmentView {
    override var commonView: MainContract.CommonView = context.commonView
}