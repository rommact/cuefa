package com.rommac.players

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.rommac.core_api.mediator.AppWithFacade
import com.rommac.mvp.BaseFragment
import com.rommac.network_api.AppWithNetwork
import com.rommac.players.databinding.PlayersFragmentBinding
import com.rommac.players.di.PlayersComponent
import javax.inject.Inject

class PlayersFragment : BaseFragment(){


    @Inject
    lateinit var playersView: PlayersViewImpl

    @Inject
    lateinit var viewModel: PlayersViewModel

    @Inject
    lateinit var binding: PlayersFragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inject()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        playersView.onFinishInflate(viewModel, lifecycle)
    }

    private fun inject(){
        PlayersComponent.create(
            (requireActivity().application as AppWithFacade).getFacade(),
            (requireActivity().application as AppWithNetwork).getNetworkFacade(),
            this
        ).inject(this)
    }

}
