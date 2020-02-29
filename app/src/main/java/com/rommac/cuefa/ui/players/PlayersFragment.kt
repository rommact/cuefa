package com.rommac.cuefa.ui.players

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.rommac.cuefa.App
import com.rommac.cuefa.R
import com.rommac.cuefa.di.DaggerFragmentComponent
import javax.inject.Inject

class PlayersFragment : Fragment(){


    lateinit var presenter: PlayersContract.Presenter
    lateinit var playersView: PlayersContract.View

    lateinit var playerListAdapter: PlayerListAdapter

    companion object {
        fun newInstance() = PlayersFragment()
    }

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
        playersView = PlayersViewImpl(this)
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
