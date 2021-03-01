package com.rommac.mvp

import android.view.View
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner

abstract class BaseView<T: BaseViewModel>(
    protected val rootView: View,
    protected val lifecycleOwner: LifecycleOwner
) : MvpView {
    lateinit var viewModel: T
    fun onFinishInflate(viewModel: T, lifecycle: Lifecycle){
        this.viewModel = viewModel
        lifecycle.addObserver(viewModel)
        initViews()
        bindViewModel()
        viewModel.viewIsReady()
    }

    abstract fun initViews()

    abstract fun bindViewModel()

    override fun showError(textId: Int) {
        Toast.makeText(rootView.context, textId, Toast.LENGTH_SHORT).show()
    }

}