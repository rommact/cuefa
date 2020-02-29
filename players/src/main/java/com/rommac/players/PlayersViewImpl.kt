package com.rommac.players

import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rommac.core_api.dto.Player
import com.rommac.cuefa.mvp.BaseView

class PlayersViewImpl(private val fragment: Fragment) : BaseView(fragment.context!!), PlayersContract.View,
    PlayerListAdapter.Listener {

    private lateinit var editQuery: EditText
    private lateinit var listPlayers: RecyclerView
    lateinit var presenter: PlayersContract.Presenter
    lateinit var playerListAdapter: PlayerListAdapter

    fun onFinishInaflate(playersPresenter: PlayersContract.Presenter): PlayersContract.View {
        listPlayers = fragment.view!!.findViewById(R.id.listPlayers)
        editQuery = fragment.view!!.findViewById(R.id.editQuery)
        presenter = playersPresenter
        presenter.attachView(this, fragment.lifecycle)
        initViews()
        return this
    }

    private fun initViews() {
        playerListAdapter = PlayerListAdapter(this)
        listPlayers.adapter = playerListAdapter
        listPlayers.layoutManager = LinearLayoutManager(fragment.activity)

        presenter.getPlayersLiveData().observe(fragment,
            Observer<List<Player>> {
                playerListAdapter.data = it
                playerListAdapter.notifyDataSetChanged()
            })

        editQuery.addTextChangedListener(afterTextChanged = {
            presenter.onQueryTextChanged(it.toString())
        })
    }

    override fun onItemClicked(player: Player) {
        presenter.onItemClicked(player)
    }
}