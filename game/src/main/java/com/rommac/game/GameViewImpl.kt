package com.rommac.game
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rommac.mvp.BaseView
import com.rommac.cuefa.network.dto.session.ACTION_TYPE
import com.rommac.cuefa.ui.game.ActionsAdapter
import com.rommac.cuefa.ui.game.GameContract

class GameViewImpl(private val activity: AppCompatActivity): BaseView(activity),
    GameContract.View, ActionsAdapter.OnItemClickListener {

    private lateinit var adapter: ActionsAdapter
    private lateinit var recActions: RecyclerView
    private lateinit var imageSelectedMy: ImageView
    private lateinit var imageSelectedOpponent: ImageView
    private lateinit var txtOpponentName: TextView
    private lateinit var progressBarMy: ProgressBar
    private lateinit var presenter: GameContract.Presenter


    fun onFinishInflate(presenter: GameContract.Presenter): GameViewImpl {
        this.presenter = presenter
        initViews()
        presenter.attachView(this, activity.lifecycle)
        presenter.viewIsReady()
        return this
    }

    private fun initViews(){
        recActions = activity.findViewById(R.id.rec_actions)
        imageSelectedMy = activity.findViewById(R.id.image_selected_my)
        imageSelectedOpponent = activity.findViewById(R.id.image_selected_opponent)
        txtOpponentName = activity.findViewById(R.id.txt_opponent_name)
        progressBarMy = activity.findViewById(R.id.progress_bar_my)

        adapter = ActionsAdapter(this)
        recActions.layoutManager = GridLayoutManager(activity, 3)
        recActions.adapter = adapter
    }

    override fun onItemClick(actionType: ACTION_TYPE) {
        presenter.onActionClicked(actionType)
    }

    override fun setSelectedActionMy(actionType: ACTION_TYPE) = runOnUiThread{
        imageSelectedMy.setImageResource(getActionIcon(actionType))
    }

    override fun setSelectedActionOpponent(actionType: ACTION_TYPE) {
        imageSelectedOpponent.setImageResource(getActionIcon(actionType))
    }


    override fun setOpponentName(name: String) = runOnUiThread{
        txtOpponentName.text = name
    }
    override fun setActionsList(actions: List<ACTION_TYPE>) = runOnUiThread{
        adapter.data = actions.map { createAction(it) }
    }

    override fun setOpponentProgressVisibility(visible: Boolean) = runOnUiThread{
        //TODO
    }

    override fun exit() {
        activity.finish()
    }

    override fun setMyProgressVisibility(visible: Boolean) = runOnUiThread{
        progressBarMy.visibility = if (visible) VISIBLE else GONE
        imageSelectedMy.visibility = if (!visible) VISIBLE else GONE
    }

    override fun setEnabledUI(enabled: Boolean) = runOnUiThread{
        adapter.isEnabled = enabled
    }

    private fun getActionIcon(actionType: ACTION_TYPE): Int =
        when(actionType){
        ACTION_TYPE.PAPER -> R.drawable.ic_insert_drive_file_black_24dp
        ACTION_TYPE.STONE -> R.drawable.ic_diamond_stone
        ACTION_TYPE.SCISSORS -> R.drawable.ic_content_cut
    }

    private fun createAction(actionType: ACTION_TYPE) = when(actionType){
        ACTION_TYPE.PAPER -> ActionsAdapter.Action(actionType, getActionIcon(actionType))
        ACTION_TYPE.STONE -> ActionsAdapter.Action(actionType, getActionIcon(actionType))
        ACTION_TYPE.SCISSORS -> ActionsAdapter.Action(actionType, getActionIcon(actionType))
    }

    private fun runOnUiThread(runnable: () -> Unit){
        activity.runOnUiThread(runnable)
    }
}