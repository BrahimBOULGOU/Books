package com.publicissapient.publicissapienttest.ui.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.synopsis_layout.view.*

class ListSynopsisViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(syponis: String) {
        itemView.synopsisText.text = syponis
    }
}