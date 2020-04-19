package com.rommac.players

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.rommac.core_api.mediator.AppWithFacade
import com.rommac.mvp.BaseFragment
import com.rommac.network_api.AppWithNetwork
import javax.inject.Inject

class PlayersFragment : BaseFragment(){


    lateinit var presenter: PlayersContract.Presenter
    lateinit var playersView: PlayersContract.View

    @Inject
    lateinit var presenterFactory: PlayerPresenterFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.players_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        inject()
        presenter = ViewModelProvider(this, presenterFactory).get(PlayersPresenter::class.java)
        playersView = PlayersViewImpl(view,lifecycle)
            .onFinishInaflate(presenter)
    }

    private fun inject(){
        PlayersComponent.create(
            (activity!!.application as AppWithFacade).getFacade(),
            (activity!!.application as AppWithNetwork).getNetworkFacade(),
            this
        ).inject(this)
    }

}
