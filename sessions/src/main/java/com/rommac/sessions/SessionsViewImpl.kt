package com.rommac.sessions

import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rommac.core_api.dto.GameSession
import com.rommac.core_api.dto.GameSessionState
import com.rommac.core_api.mediator.GameMediator
import com.rommac.mvp.BaseChildView
import com.rommac.mvp.CommonView
import com.rommac.ui_core.BottomMenuItem
import com.rommac.ui_core.BottomSheetMenu


class SessionsViewImpl(private val rootView:View, private val lifecycle:Lifecycle,
                       private val gameMediator: GameMediator,commonView: CommonView) : BaseChildView(commonView, rootView.context),
    SessionsContract.View,
    SessionListAdapter.Listener {

    private lateinit var listPlayers: RecyclerView
    private lateinit var btnAddSession: View
    lateinit var presenter: SessionsContract.Presenter
    lateinit var sessionListAdapter: SessionListAdapter


    override fun onFinishInaflate(playersPresenter: SessionsContract.Presenter) {
        listPlayers = rootView.findViewById(R.id.list_players)
        btnAddSession = rootView.findViewById(R.id.btn_add_session)
        presenter = playersPresenter
        presenter.attachView(this, lifecycle)
        initViews()
        presenter.viewIsReady()
    }

    private fun initViews() {
        sessionListAdapter = SessionListAdapter(this)
        listPlayers.adapter = sessionListAdapter
        listPlayers.layoutManager = LinearLayoutManager(rootView.context)

        btnAddSession.setOnClickListener {
            presenter.onAddSessionClicked()
        }
    }

    override fun onItemClicked(gameSession: GameSession) {
        presenter.onItemClicked(gameSession)
    }

    override fun showConfirmSessionCreation() {
        val items: ArrayList<BottomMenuItem> = ArrayList()
        val bottomDialog = BottomSheetMenu(rootView.context, items, R.string.create_session_confirm)
        items.addAll(
            arrayListOf(
                BottomMenuItem(R.drawable.ic_check_black_24dp, R.string.ok) {
                    presenter.onCreationConfirmedClicked()
                },
                BottomMenuItem(R.drawable.ic_close_black_24dp, R.string.cancel) {
                    bottomDialog.dismiss()
                }
            )
        )
        bottomDialog.show()

    }

    override fun showJoinConfirm(gameSession: GameSession) {
        val items: ArrayList<BottomMenuItem> = ArrayList()
        val bottomDialog = BottomSheetMenu(rootView.context, items, R.string.create_session_confirm)
        items.addAll(
            arrayListOf(
                BottomMenuItem(R.drawable.ic_check_black_24dp, R.string.ok) {
                    presenter.onJoinConfirmedClicked(gameSession)
                },
                BottomMenuItem(R.drawable.ic_close_black_24dp, R.string.cancel) {
                    bottomDialog.dismiss()
                }
            )
        )
        bottomDialog.show()
    }

    override fun setSessions(it: List<GameSession>) {
        sessionListAdapter.data = it
        sessionListAdapter.notifyDataSetChanged()
    }

    override fun toGame(
        gameSession: GameSession,
        state: GameSessionState
    ) {
        gameMediator.toGame(rootView.context, gameSession,state)
    }
}