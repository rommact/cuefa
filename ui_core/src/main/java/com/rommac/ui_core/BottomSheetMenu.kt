package com.rommac.ui_core

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.bottom_sheet_menu.view.*
import kotlinx.android.synthetic.main.item_bottom_sheet_menu1.view.*

class BottomSheetMenu(
    context: Context,
    items: List<BottomMenuItem>, @StringRes val title: Int
) {
    private var adapter: BottomSheetMenuAdapter
    private val bottomSheetDialog: BottomSheetDialog = BottomSheetDialog(context)

    init {
        val view = LayoutInflater.from(context).inflate(R.layout.bottom_sheet_menu, null)
        bottomSheetDialog.setContentView(view)
        adapter = BottomSheetMenuAdapter(items)
        with(view) {
            bottom_sheet_recycler.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            bottom_sheet_recycler.adapter = adapter
            txt_title.setText(title)
        }
    }

    fun show() {
        adapter.notifyDataSetChanged()
        bottomSheetDialog.show()

    }

    fun dismiss() {
        bottomSheetDialog.dismiss()
    }

    inner class BottomSheetMenuAdapter(private val items: List<BottomMenuItem>) :
        RecyclerView.Adapter<BottomSheetMenuViewHolder>() {

        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): BottomSheetMenuViewHolder {
            return BottomSheetMenuViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.item_bottom_sheet_menu1,
                    parent,
                    false
                )
            )
        }

        override fun getItemCount(): Int = items.size

        override fun onBindViewHolder(holder: BottomSheetMenuViewHolder, position: Int) {
            holder.bind(items[position])
        }


    }

    inner class BottomSheetMenuViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        fun bind(item: BottomMenuItem) {
            with(view) {
                bottom_menu_title.text = view.context.getText(item.name)
                bottom_menu_icon.setImageResource(item.resId)

                setOnClickListener {
                    item.action()
                    dismiss()
                }
            }
        }
    }
}