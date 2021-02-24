package com.rommac.sessions


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rommac.core_api.mediator.AppWithFacade
import com.rommac.mvp.BaseFragment
import com.rommac.network_api.AppWithNetwork
import com.rommac.sessions.databinding.FragmentSessionBinding
import com.rommac.sessions.di.SessionsComponent
import javax.inject.Inject


class SessionFragment : BaseFragment() {

    @Inject
    lateinit var sessionView: SessionsViewImpl

    @Inject
    lateinit var viewModel: SessionsViewModel

    @Inject
    lateinit var fragmentSessionBinding: FragmentSessionBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        inject()
        return fragmentSessionBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sessionView
            .onFinishInflate(viewModel, lifecycle)
    }

    private fun inject() {
        SessionsComponent.create(
            (requireActivity().application as AppWithFacade).getFacade(),
            (requireActivity().application as AppWithNetwork).getNetworkFacade(), this
        ).inject(this)
    }

}
