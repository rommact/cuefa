package com.rommac.players

import android.view.View
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rommac.core_api.dto.Player
import com.rommac.mvp.BaseView

class PlayersViewImpl(private val rootView: View, private val lifecycle: Lifecycle) : BaseView(rootView.context), PlayersContract.View,
    PlayerListAdapter.Listener {

    private lateinit var editQuery: EditText
    private lateinit var listPlayers: RecyclerView
    lateinit var presenter: PlayersContract.Presenter
    lateinit var playerListAdapter: PlayerListAdapter

    fun onFinishInaflate(playersPresenter: PlayersContract.Presenter): PlayersContract.View {
        listPlayers = rootView.findViewById(R.id.list_players)
        editQuery = rootView.findViewById(R.id.edit_query)
        presenter = playersPresenter
        presenter.attachView(this, lifecycle)
        initViews()
        return this
    }

    private fun initViews() {
        playerListAdapter = PlayerListAdapter(this)
        listPlayers.adapter = playerListAdapter
        listPlayers.layoutManager = LinearLayoutManager(rootView.context)
        editQuery.addTextChangedListener(afterTextChanged = {
            presenter.onQueryTextChanged(it.toString())
        })
    }

    override fun setPlayers(players: List<Player>) {
        playerListAdapter.data = players
        playerListAdapter.notifyDataSetChanged()
    }

    override fun onItemClicked(player: Player) {
        presenter.onItemClicked(player)
    }
}