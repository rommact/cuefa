package com.rommac.sessions


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rommac.core_api.mediator.AppWithFacade
import com.rommac.core_api.mediator.MediatorsOwner
import com.rommac.core_api.mediator.MediatorsProvider
import com.rommac.mvp.BaseFragment
import com.rommac.network_api.AppWithNetwork
import com.rommac.sessions.databinding.FragmentSessionBinding
import com.rommac.sessions.di.SessionsComponent
import javax.inject.Inject


class SessionFragment : BaseFragment() {

    private lateinit var mediatorOwner: MediatorsOwner

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

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is MediatorsOwner){
            mediatorOwner = context as MediatorsOwner
        }
    }

    private fun inject() {
        SessionsComponent.create(
            (requireActivity().application as AppWithFacade).getFacade(),
            (requireActivity().application as AppWithNetwork).getNetworkFacade(), this,
                mediatorOwner.getMediatorsProvider()
        ).inject(this)
    }

}
