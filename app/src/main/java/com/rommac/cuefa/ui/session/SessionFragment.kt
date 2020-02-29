package com.rommac.cuefa.ui.session


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.rommac.cuefa.App

import com.rommac.cuefa.R
import com.rommac.cuefa.di.DaggerFragmentComponent
import com.rommac.cuefa.ui.base.BaseFragment
import javax.inject.Inject


class SessionFragment : BaseFragment() {
    lateinit var presenter: SessionsContract.Presenter
    lateinit var sessionView: SessionsContract.View


    @Inject
    lateinit var presenterFactory: SessionPresenterFactory
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_session, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        inject()
        presenter = ViewModelProvider(this, presenterFactory).get(SessionsPresenter::class.java)
        sessionView = SessionsViewImpl(this)
            .onFinishInaflate(presenter)
    }

    private fun inject(){
        DaggerFragmentComponent
            .builder()
            .appComponent(App.appComponent)
            .build()
            .inject(this)
    }

}
