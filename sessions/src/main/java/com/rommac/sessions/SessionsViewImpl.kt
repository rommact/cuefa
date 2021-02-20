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
import com.rommac.ui_core.BottomMenuItem
import com.rommac.ui_core.BottomSheetMenu


class SessionsViewImpl(
    rootView: View, lifecycleOwner: LifecycleOwner,
    private val gameMediator: GameMediator
) : BaseView<SessionsViewModel>(rootView, lifecycleOwner),
    SessionListAdapter.Listener {

    private lateinit var listPlayers: RecyclerView
    private lateinit var btnAddSession: View
    lateinit var sessionListAdapter: SessionListAdapter


    override fun initViews() {
        listPlayers = rootView.findViewById(R.id.list_players)
        btnAddSession = rootView.findViewById(R.id.btn_add_session)
        sessionListAdapter = SessionListAdapter(this)
        listPlayers.adapter = sessionListAdapter
        listPlayers.layoutManager = LinearLayoutManager(rootView.context)

        btnAddSession.setOnClickListener {
            viewModel.onAddSessionClicked()
        }
    }

    override fun onItemClicked(gameSession: GameSession) {
        viewModel.onItemClicked(gameSession)
    }

    override fun bindViewModel() {
        viewModel.sessions.observe(lifecycleOwner, Observer {
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