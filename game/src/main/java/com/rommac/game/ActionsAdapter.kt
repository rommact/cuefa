package com.rommac.game

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.rommac.core_api.network.dto.session.ACTION_TYPE

class ActionsAdapter(private val onItemClickListener: OnItemClickListener) :
    RecyclerView.Adapter<ActionsAdapter.ViewHolder>() {

    var data: List<Action> = ArrayList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    var isEnabled = true
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.action_item, parent, false)
        return ViewHolder(
            view
        )
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val action = data[position]
        holder.imageIcon.setImageResource(action.iconResId)
        if (isEnabled) {
            holder.itemView.setOnClickListener {
                onItemClickListener.onItemClick(action.actionType)
            }
            holder.imageIcon.imageAlpha = 255
        } else {
            holder.itemView.setOnClickListener(null)
            holder.imageIcon.imageAlpha = 100
        }

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageIcon: ImageView = itemView.findViewById(R.id.image_icon)

    }

    data class Action(
        val actionType: ACTION_TYPE,
        val iconResId: Int
    )

    interface OnItemClickListener {
        fun onItemClick(actionType: ACTION_TYPE)
    }
}