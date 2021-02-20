package com.rommac.sessions


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rommac.core_api.mediator.AppWithFacade
import com.rommac.mvp.BaseFragment
import com.rommac.network_api.AppWithNetwork
import com.rommac.sessions.di.SessionsComponent
import javax.inject.Inject


class SessionFragment : BaseFragment() {

    @Inject
    lateinit var sessionView: SessionsViewImpl


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
        val viewModel = ViewModelProvider(this, presenterFactory).get(SessionsViewModel::class.java)
        sessionView
            .onFinishInflate(viewModel,lifecycle)
    }

    private fun inject() {
        SessionsComponent.create((activity!!.application as AppWithFacade).getFacade(),
            (activity!!.application as AppWithNetwork).getNetworkFacade(),this).inject(this)
    }

}
