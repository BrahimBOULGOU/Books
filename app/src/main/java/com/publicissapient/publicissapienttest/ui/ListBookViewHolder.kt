package com.publicissapient.publicissapienttest.ui

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.publicissapient.publicissapienttest.adapters.ListBookAdapter
import com.publicissapient.publicissapienttest.models.datamodel.Book
import kotlinx.android.synthetic.main.book_item_layout.view.*

class ListBookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


    fun bind(book: Book, listener: ListBookAdapter.ItemClickListener) {
      itemView.textView.text = book.title
      itemView.textView3.text = book.price.toString()
      Glide.with(itemView)
            .load(book.image)
            .into(itemView.imageView)

        itemView.setOnClickListener {
            listener.onItemClickListener(book)
        }
    }



}
