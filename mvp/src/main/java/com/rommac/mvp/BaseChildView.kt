package com.rommac.mvp

import android.content.Context


open class BaseChildView(override var commonView: CommonView, context: Context) : BaseView(context), ChildView {
}