package com.publicissapient.publicissapienttest.ui.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.publicissapient.publicissapienttest.adapters.ListBookAdapter
import com.publicissapient.publicissapienttest.models.datamodel.Book
import kotlinx.android.synthetic.main.book_item_layout.view.*
import java.text.NumberFormat

class ListBookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(book: Book, listener: ListBookAdapter.ItemClickListener) {
        itemView.bookTitle.text = book.title

        itemView.bookPrice.text = NumberFormat.getInstance().format(book.price).toString().plus(" €")
        itemView.isPurchased.text = if(book.isSold) "Acheté" else "Non"
        
        Glide.with(itemView)
            .load(book.image)
            .into(itemView.imageBook)

        itemView.setOnClickListener {
            listener.onItemClickListener(book)
        }
    }

}
