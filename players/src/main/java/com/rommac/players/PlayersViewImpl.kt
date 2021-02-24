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
import com.rommac.players.databinding.PlayersFragmentBinding

class PlayersViewImpl(private val binding: PlayersFragmentBinding,
   lifecycleOwner:LifecycleOwner) :
    BaseView<PlayersViewModel>(binding.root, lifecycleOwner),
    PlayerListAdapter.Listener {

    lateinit var playerListAdapter: PlayerListAdapter


    override fun initViews() {
        playerListAdapter = PlayerListAdapter(this)
        binding.listPlayers.adapter = playerListAdapter
        binding.listPlayers.layoutManager = LinearLayoutManager(rootView.context)
        binding.editQuery.addTextChangedListener(afterTextChanged = {
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