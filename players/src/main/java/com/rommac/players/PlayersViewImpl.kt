package com.rommac.players

import android.view.View
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rommac.core_api.dto.Player
import com.rommac.mvp.BaseView

class PlayersViewImpl(rootView: View, lifecycleOwner:LifecycleOwner) :
    BaseView<PlayersViewModel>(rootView, lifecycleOwner),
    PlayerListAdapter.Listener {

    private lateinit var editQuery: EditText
    private lateinit var listPlayers: RecyclerView
    lateinit var playerListAdapter: PlayerListAdapter


    override fun initViews() {
        listPlayers = rootView.findViewById(R.id.list_players)
        editQuery = rootView.findViewById(R.id.edit_query)

        playerListAdapter = PlayerListAdapter(this)
        listPlayers.adapter = playerListAdapter
        listPlayers.layoutManager = LinearLayoutManager(rootView.context)
        editQuery.addTextChangedListener(afterTextChanged = {
            viewModel.onQueryTextChanged(it.toString())
        })
    }

    override fun bindViewModel() {
        viewModel.players.observe(lifecycleOwner, Observer {
            setPlayers(it)
        })
    }

    private fun setPlayers(players: List<Player>) {
        playerListAdapter.data = players
        playerListAdapter.notifyDataSetChanged()
    }

    override fun onItemClicked(player: Player) {
        viewModel.onItemClicked(player)
    }
}