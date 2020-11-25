package com.publicissapient.publicissapienttest.ui

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.publicissapient.publicissapienttest.adapters.ListBookAdapter
import com.publicissapient.publicissapienttest.models.datamodel.Book
import kotlinx.android.synthetic.main.book_item_layout.view.*
import kotlinx.android.synthetic.main.synopsis_layout.view.*

class ListSynopsisViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(syponis: String) {
        itemView.textView10.text = syponis
    }
}