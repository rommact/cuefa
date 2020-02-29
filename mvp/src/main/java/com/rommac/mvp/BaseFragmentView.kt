package com.rommac.mvp


open class BaseFragmentView(context: BaseFragment) : BaseView(context.context!!), FragmentView {
    override var commonView: CommonView = context.commonView
}