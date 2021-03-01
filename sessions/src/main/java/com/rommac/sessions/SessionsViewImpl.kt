package com.rommac.sessions

import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rommac.core_api.EventObserver
import com.rommac.core_api.dto.GameSession
import com.rommac.core_api.dto.GameSessionState
import com.rommac.core_api.mediator.GameMediator
import com.rommac.mvp.BaseView
import com.rommac.sessions.databinding.FragmentSessionBinding
import com.rommac.ui_core.BottomMenuItem
import com.rommac.ui_core.BottomSheetMenu


class SessionsViewImpl(private val binding: FragmentSessionBinding,
    lifecycleOwner: LifecycleOwner,
    private val gameMediator: GameMediator
) : BaseView<SessionsViewModel>(binding.root, lifecycleOwner),
    SessionListAdapter.Listener {

    lateinit var sessionListAdapter: SessionListAdapter


    override fun initViews() {
        sessionListAdapter = SessionListAdapter(this)
        binding.listPlayers.adapter = sessionListAdapter
        binding.listPlayers.layoutManager = LinearLayoutManager(rootView.context)

        binding.btnAddSession.setOnClickListener {
            viewModel.onAddSessionClicked()
        }
    }

    override fun onItemClicked(gameSession: GameSession) {
        viewModel.onItemClicked(gameSession)
    }

    override fun bindViewModel() {
        viewModel.sessions.observe(lifecycleOwner, {
            setSessions(it)
        })

        viewModel.toGame.observe(lifecycleOwner, EventObserver {
            toGame(it.gameSession, it.gameSessionState)
        })

        viewModel.showConfirmSessionCreation.observe(lifecycleOwner, EventObserver {
            showConfirmSessionCreation()
        })

        viewModel.showJoinConfirm.observe(lifecycleOwner, EventObserver {
            showJoinConfirm(it)
        })

    }

    private fun showConfirmSessionCreation() {
        val items: ArrayList<BottomMenuItem> = ArrayList()
        val bottomDialog = BottomSheetMenu(rootView.context, items, R.string.create_session_confirm)
        items.addAll(
            arrayListOf(
                BottomMenuItem(R.drawable.ic_check_black_24dp, R.string.ok) {
                    viewModel.onCreationConfirmedClicked()
                },
                BottomMenuItem(R.drawable.ic_close_black_24dp, R.string.cancel) {
                    bottomDialog.dismiss()
                }
            )
        )
        bottomDialog.show()

    }

    private fun showJoinConfirm(gameSession: GameSession) {
        val items: ArrayList<BottomMenuItem> = ArrayList()
        val bottomDialog = BottomSheetMenu(rootView.context, items, R.string.join_session_confirm)
        items.addAll(
            arrayListOf(
                BottomMenuItem(R.drawable.ic_check_black_24dp, R.string.ok) {
                    viewModel.onJoinConfirmedClicked(gameSession)
                },
                BottomMenuItem(R.drawable.ic_close_black_24dp, R.string.cancel) {
                    bottomDialog.dismiss()
                }
            )
        )
        bottomDialog.show()
    }

    private fun setSessions(it: List<GameSession>) {
        sessionListAdapter.data = it
        sessionListAdapter.notifyDataSetChanged()
    }

    private fun toGame(
        gameSession: GameSession,
        state: GameSessionState
    ) {
        gameMediator.toGame(rootView.context, gameSession, state)
    }
}