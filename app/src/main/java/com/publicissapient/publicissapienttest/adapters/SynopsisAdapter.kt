package com.publicissapient.publicissapienttest.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.publicissapient.publicissapienttest.R
import com.publicissapient.publicissapienttest.ui.viewholders.ListSynopsisViewHolder

class SynopsisAdapter(private val synopsis: ArrayList<String>) :
    RecyclerView.Adapter<ListSynopsisViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListSynopsisViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val inflatedView = inflater.inflate(R.layout.synopsis_layout, null)
        return ListSynopsisViewHolder(
            inflatedView
        )
    }

    override fun getItemCount(): Int = synopsis.size

    override fun onBindViewHolder(holder: ListSynopsisViewHolder, position: Int) {
        holder.bind(synopsis.get(position))
    }
}