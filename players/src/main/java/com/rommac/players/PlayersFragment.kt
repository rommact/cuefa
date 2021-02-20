package com.rommac.players

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.rommac.core_api.mediator.AppWithFacade
import com.rommac.mvp.BaseFragment
import com.rommac.network_api.AppWithNetwork
import com.rommac.players.di.PlayersComponent
import javax.inject.Inject

class PlayersFragment : BaseFragment(){


    @Inject
    lateinit var playersView: PlayersViewImpl

    @Inject
    lateinit var presenterFactory: ViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.players_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        inject()
        val viewModel = ViewModelProvider(this, presenterFactory).get(PlayersViewModel::class.java)
        playersView.onFinishInflate(viewModel, lifecycle)
    }

    private fun inject(){
        PlayersComponent.create(
            (activity!!.application as AppWithFacade).getFacade(),
            (activity!!.application as AppWithNetwork).getNetworkFacade(),
            this
        ).inject(this)
    }

}
