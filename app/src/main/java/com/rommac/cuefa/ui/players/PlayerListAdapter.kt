package com.rommac.cuefa.ui.players

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rommac.cuefa.core.Player

import android.view.LayoutInflater
import android.widget.TextView
import com.rommac.cuefa.R


class PlayerListAdapter(val listener: Listener): RecyclerView.Adapter<PlayerListAdapter.PlayerViewHolder>() {
    var data:List<Player> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.player_item_view, parent, false)
        return PlayerViewHolder(
            view
        )
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        holder.bind(data[position].email)
    }

    class PlayerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtName:TextView = itemView.findViewById(R.id.txtName)
        fun bind(name:String){
            txtName.text = name
        }
    }

    override fun onViewAttachedToWindow(holder: PlayerViewHolder) {
        super.onViewAttachedToWindow(holder)
        holder.itemView.setOnClickListener {
            listener.onItemClicked(data[holder.adapterPosition])
        }
    }

    override fun onViewDetachedFromWindow(holder: PlayerViewHolder) {
        super.onViewDetachedFromWindow(holder)
        holder.itemView.setOnClickListener(null)
    }

    interface Listener{
        fun onItemClicked(player: Player)
    }
}