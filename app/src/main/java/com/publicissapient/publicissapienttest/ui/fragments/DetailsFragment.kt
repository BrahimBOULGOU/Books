package com.publicissapient.publicissapienttest.ui.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.transition.TransitionInflater
import com.bumptech.glide.Glide
import com.publicissapient.publicissapienttest.R
import com.publicissapient.publicissapienttest.adapters.SynopsisAdapter
import com.publicissapient.publicissapienttest.ui.fragments.DetailsFragmentArgs.Companion.fromBundle
import kotlinx.android.synthetic.main.details_custom_menu.view.*
import kotlinx.android.synthetic.main.fragment_details.*


class DetailsFragment : Fragment() {

    val book by lazy {
        fromBundle(requireArguments()).book
    }
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var adapter: SynopsisAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        activity?.title = "Infos sur le livre"
        sharedElementEnterTransition =
            TransitionInflater.from(context).inflateTransition(android.R.transition.move)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.details_menu, menu)

        val item: MenuItem = menu.findItem(R.id.action_close)
        item.actionView.closeDetailsBtn.setOnClickListener {
            activity?.onBackPressed()
        }
    }


    private fun initView(view: View) {
        Glide.with(view)
            .load(book.image)
            .into(imageBook)
        bookTitle.text = book.title
        addToBasket.text = if (book.isSold) "Supprimer du panier" else "Ajouter au panier"

        addToBasket.setOnClickListener {
            book.isSold = !book.isSold
            activity?.onBackPressed()
        }

        setUpRecyclerView()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView(view)
    }
    private fun setUpRecyclerView() {
        linearLayoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        synopsisRecyclerView.layoutManager = linearLayoutManager
        adapter = SynopsisAdapter(book.description)
        synopsisRecyclerView.adapter = adapter
    }
}