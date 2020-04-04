package com.rommac.sessions

import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rommac.core_api.dto.GameSession
import com.rommac.mvp.BaseFragment
import com.rommac.mvp.BaseChildView
import com.rommac.mvp.CommonView
import com.rommac.ui_core.BottomMenuItem
import com.rommac.ui_core.BottomSheetMenu


class SessionsViewImpl(private val rootView:View, private val lifecycle:Lifecycle, commonView: CommonView) : BaseChildView(commonView, rootView.context),
    SessionsContract.View,
    SessionListAdapter.Listener {

    private lateinit var listPlayers: RecyclerView
    private lateinit var btnAddSession: View
    lateinit var presenter: SessionsContract.Presenter
    lateinit var sessionListAdapter: SessionListAdapter


    fun onFinishInaflate(playersPresenter: SessionsContract.Presenter): SessionsContract.View {
        listPlayers = rootView.findViewById(R.id.list_players)
        btnAddSession = rootView.findViewById(R.id.btn_add_session)
        presenter = playersPresenter
        presenter.attachView(this, lifecycle)
        initViews()
        presenter.viewIsReady()
        return this
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
        val bottomDialog = BottomSheetMenu(rootView.context, items)
        items.addAll(
            arrayListOf(
                BottomMenuItem(R.drawable.ic_check_black_24dp, R.string.ok) {
                    presenter.onCreationConfirmedClicked()
                },
                BottomMenuItem(R.drawable.ic_close_black_24dp, R.string.cancel) {
                    presenter.onCreationCanceledClicked()
                }
            )
        )
        bottomDialog.show()

    }

    override fun setSessions(it: List<GameSession>) {
        sessionListAdapter.data = it
        sessionListAdapter.notifyDataSetChanged()
    }
}