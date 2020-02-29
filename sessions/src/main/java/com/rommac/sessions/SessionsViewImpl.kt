package com.rommac.cuefa.ui.session

import android.view.View
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.rommac.cuefa.R
import com.rommac.cuefa.core.GameSession
import com.rommac.cuefa.core.Player
import com.rommac.cuefa.mvp.BaseFragmentView
import com.rommac.cuefa.mvp.BaseView
import com.rommac.cuefa.ui.base.BaseFragment
import com.rommac.cuefa.ui.common.BottomMenuItem
import com.rommac.cuefa.ui.common.BottomSheetMenu
import com.rommac.cuefa.ui.main.MainContract

class SessionsViewImpl(private val fragment: BaseFragment) : BaseFragmentView(fragment), SessionsContract.View,
    SessionListAdapter.Listener {

    private lateinit var listPlayers: RecyclerView
    private lateinit var btnAddSession: View
    lateinit var presenter: SessionsContract.Presenter
    lateinit var sessionListAdapter: SessionListAdapter


    fun onFinishInaflate(playersPresenter: SessionsContract.Presenter): SessionsContract.View {
        listPlayers = fragment.view!!.findViewById(R.id.listPlayers)
        btnAddSession = fragment.view!!.findViewById(R.id.btn_add_session)
        presenter = playersPresenter
        presenter.attachView(this, fragment.lifecycle)
        initViews()
        presenter.viewIsReady()
        return this
    }

    private fun initViews() {
        sessionListAdapter = SessionListAdapter(this)
        listPlayers.adapter = sessionListAdapter
        listPlayers.layoutManager = LinearLayoutManager(fragment.activity)

        btnAddSession.setOnClickListener {
            presenter.onAddSessionClicked()
        }

        presenter.getSessionsLiveData().observe(fragment,
            Observer<List<GameSession>> {
                sessionListAdapter.data = it
                sessionListAdapter.notifyDataSetChanged()
            })

    }

    override fun onItemClicked(gameSession: GameSession) {
        presenter.onItemClicked(gameSession)
    }

    override fun showConfirmSessionCreation() {
        val items: ArrayList<BottomMenuItem> = ArrayList()
        val  bottomDialog = BottomSheetMenu(fragment.context!!,items)
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
}