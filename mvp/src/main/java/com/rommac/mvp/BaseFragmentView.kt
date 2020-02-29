package com.rommac.cuefa.mvp


import com.rommac.mvp.BaseFragment
import com.rommac.mvp.CommonView

open class BaseFragmentView(context: BaseFragment) : BaseView(context.context!!), FragmentView {
    override var commonView: CommonView = context.commonView
}