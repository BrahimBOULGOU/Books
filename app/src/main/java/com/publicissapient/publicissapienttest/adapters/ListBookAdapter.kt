package com.publicissapient.publicissapienttest.adapters


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.publicissapient.publicissapienttest.R
import com.publicissapient.publicissapienttest.models.datamodel.Book
import com.publicissapient.publicissapienttest.ui.ListBookViewHolder

class ListBookAdapter (private val books: ArrayList<Book>): RecyclerView.Adapter<ListBookViewHolder>() {
    lateinit var listener: ItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListBookViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context);
        val inflatedView = inflater.inflate(R.layout.book_item_layout, null)
        return ListBookViewHolder(inflatedView)
    }

    override fun getItemCount(): Int = books.size

    override fun onBindViewHolder(holder: ListBookViewHolder, position: Int) {
        holder.bind(books.get(position), listener)
    }

    interface ItemClickListener {
        fun onItemClickListener(item: Book)
    }
}

