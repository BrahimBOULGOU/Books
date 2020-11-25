package com.publicissapient.publicissapienttest.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.util.Util
import com.google.gson.JsonObject
import com.publicissapient.publicissapienttest.R
import com.publicissapient.publicissapienttest.adapters.ListBookAdapter
import com.publicissapient.publicissapienttest.databinding.FragmentBasketBinding
import com.publicissapient.publicissapienttest.models.datamodel.Book
import com.publicissapient.publicissapienttest.models.datamodel.Books
import com.publicissapient.publicissapienttest.models.datamodel.Offers
import com.publicissapient.publicissapienttest.netwroks.Resource
import com.publicissapient.publicissapienttest.netwroks.Status
import com.publicissapient.publicissapienttest.utils.Utils
import com.publicissapient.publicissapienttest.viewmodels.BasketViewModel
import kotlinx.android.synthetic.main.fragment_basket.*
import org.json.JSONObject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.dsl.module
import java.util.ArrayList

val viewFragment = module {
    factory { ListBooksFragment() }
    factory { BasketFragment() }
}

class BasketFragment : Fragment() {
    private val basketViewModel: BasketViewModel by viewModel()
    private lateinit var binding: FragmentBasketBinding

    private var booksList: ArrayList<Book> = ArrayList()

    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var adapter: ListBookAdapter

    val books by lazy {
        BasketFragmentArgs.fromBundle(requireArguments()).books
    }

    private val observer = Observer<Resource<Offers>> {
        when (it.status) {
            Status.SUCCESS -> updateView(it.data!!)
            Status.ERROR -> showError(it.message!!)
            Status.LOADING -> showLoading()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_basket, container, false)
        val soldBooksList: List<Book> = books.books.filter { s -> s.isSold }
        booksList.clear()
        booksList.addAll(soldBooksList)
        basketViewModel.offer.observe(viewLifecycleOwner, observer)
        linearLayoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.soldBooksRecyclerView.layoutManager = linearLayoutManager
        binding.viewModel = basketViewModel

        adapter = ListBookAdapter(booksList)
        binding.soldBooksRecyclerView.adapter = adapter

        basketViewModel.getOffers("c8fabf68-8374-48fe-a7ea-a00ccd07afff,a460afed-e5e7-4e39-a39d-c885c05db861")

        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter.listener = object : ListBookAdapter.ItemClickListener {
            override fun onItemClickListener(item: Book) {
            }
        }

    }
    private fun showError(message: String) {
        Log.d("Mydta", "error"+message)
    }

    private fun updateView(offers: Offers){
        val totalPrice: Float = booksList.map { it.price }.sum()
        val newprice = Utils.applyOffer(offers, Books(booksList), totalPrice)
        val pomo = totalPrice - newprice!!
        textView5.text = totalPrice.toString()
        textView4.text = newprice.toString()
        textView6.text = pomo.toString()
        Log.d("Mydta", "data"+offers.offers.get(0).type)

    }

    private fun showLoading() {
        Log.d("Mydta", "loading")
    }
}