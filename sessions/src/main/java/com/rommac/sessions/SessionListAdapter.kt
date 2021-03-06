package com.rommac.sessions

import android.view.View
import android.view.ViewGroup


import android.view.LayoutInflater
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rommac.core_api.dto.GameSession
import com.rommac.core_api.dto.getOpponent
import com.rommac.core_api.dto.getOwner


class SessionListAdapter(val listener: Listener): RecyclerView.Adapter<SessionListAdapter.SessionViewHolder>() {
    var data:List<GameSession> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SessionViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.session_item, parent, false)
        return SessionViewHolder(
            view
        )
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: SessionViewHolder, position: Int) {
        holder.bind(data[position])
    }

    class SessionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val txtOpponentName:TextView = itemView.findViewById(R.id.txt_opponent_name)
        private val txtSessionOwner:TextView = itemView.findViewById(R.id.txt_session_owner)
        private val txtSessionStatus:TextView = itemView.findViewById(R.id.txt_session_status)
        fun bind(session: GameSession){
            val opponent = session.getOpponent()
            val owner = session.getOwner()
            if(opponent != null){
                txtOpponentName.text = opponent.email
            }else{
                txtOpponentName.text = ""
            }

            if(owner != null){
                txtSessionOwner.text = owner.email
            }else{
                txtSessionOwner.text = "error..."
            }

            txtSessionStatus.text = session.status.name


        }
    }

    override fun onViewAttachedToWindow(holder: SessionViewHolder) {
        super.onViewAttachedToWindow(holder)
        holder.itemView.setOnClickListener {
            listener.onItemClicked(data[holder.adapterPosition])
        }
    }

    override fun onViewDetachedFromWindow(holder: SessionViewHolder) {
        super.onViewDetachedFromWindow(holder)
        holder.itemView.setOnClickListener(null)
    }

    interface Listener{
        fun onItemClicked(gameSession: GameSession)
    }
}