package com.rommac.game

import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rommac.core_api.dto.ACTION_TYPE
import com.rommac.mvp.BaseView

class GameViewImpl(rootView: View, lifecycleOwner: LifecycleOwner) :
    BaseView<GameViewModelImpl>(rootView, lifecycleOwner),
    ActionsAdapter.OnItemClickListener {

    private lateinit var adapter: ActionsAdapter
    private lateinit var recActions: RecyclerView
    private lateinit var imageSelectedMy: ImageView
    private lateinit var imageSelectedOpponent: ImageView
    private lateinit var txtOpponentName: TextView
    private lateinit var progressBarMy: ProgressBar
    private lateinit var progressbarOpponent: ProgressBar

    override fun initViews() {
        recActions = rootView.findViewById(R.id.rec_actions)
        imageSelectedMy = rootView.findViewById(R.id.image_selected_my)
        imageSelectedOpponent = rootView.findViewById(R.id.image_selected_opponent)
        txtOpponentName = rootView.findViewById(R.id.txt_opponent_name)
        progressBarMy = rootView.findViewById(R.id.progress_bar_my)
        progressbarOpponent = rootView.findViewById(R.id.progressbar_opponent)

        adapter = ActionsAdapter(this)
        recActions.layoutManager = GridLayoutManager(rootView.context, 3)
        recActions.adapter = adapter

    }

    override fun bindViewModel() {
        viewModel.actionList.observe(lifecycleOwner, {
            setActionsList(it)
        })

        viewModel.myAction.observe(lifecycleOwner, {
            setSelectedActionMy(it)
        })

        viewModel.opponentAction.observe(lifecycleOwner,  {
            setSelectedActionOpponent(it)
        })

        viewModel.opponentInProgress.observe(lifecycleOwner,  {
            setOpponentProgressVisibility(it)
        })

        viewModel.opponentName.observe(lifecycleOwner,  {
            setOpponentName(it)
        })

        viewModel.uiIsEnabled.observe(lifecycleOwner,  {
            setEnabledUI(it)
        })
        viewModel.playerInProgress.observe(lifecycleOwner,  {
            setMyProgressVisibility(it)
        })
    }

    override fun onItemClick(actionType: ACTION_TYPE) {
        viewModel.onActionClicked(actionType)
    }

    fun setSelectedActionMy(actionType: ACTION_TYPE) {
        imageSelectedMy.setImageResource(getActionIcon(actionType))
    }

    fun setSelectedActionOpponent(actionType: ACTION_TYPE) {
        imageSelectedOpponent.setImageResource(getActionIcon(actionType))
        imageSelectedOpponent.visibility = VISIBLE
    }


    fun setOpponentName(name: String) {
        txtOpponentName.text = name
    }

    fun setActionsList(actions: List<ACTION_TYPE>) {
        adapter.data = actions.map { createAction(it) }
    }

    fun setOpponentProgressVisibility(visible: Boolean) {
//        progressbarOpponent.visibility = if(visible) View.VISIBLE else View.GONE
        imageSelectedOpponent.visibility = if (!visible) VISIBLE else GONE
    }

//     fun exit() {
//        rootView.finish()
//    }

    fun setMyProgressVisibility(visible: Boolean) {
        progressBarMy.visibility = if (visible) VISIBLE else GONE
        imageSelectedMy.visibility = if (!visible) VISIBLE else GONE
    }

    fun setEnabledUI(enabled: Boolean) {
        adapter.isEnabled = enabled
    }

    private fun getActionIcon(actionType: ACTION_TYPE): Int =
        when (actionType) {
            ACTION_TYPE.PAPER -> R.drawable.ic_insert_drive_file_black_24dp
            ACTION_TYPE.STONE -> R.drawable.ic_diamond_stone
            ACTION_TYPE.SCISSORS -> R.drawable.ic_content_cut
        }

    private fun createAction(actionType: ACTION_TYPE) = when (actionType) {
        ACTION_TYPE.PAPER -> ActionsAdapter.Action(actionType, getActionIcon(actionType))
        ACTION_TYPE.STONE -> ActionsAdapter.Action(actionType, getActionIcon(actionType))
        ACTION_TYPE.SCISSORS -> ActionsAdapter.Action(actionType, getActionIcon(actionType))
    }

}