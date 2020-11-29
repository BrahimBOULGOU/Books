package com.publicissapient.publicissapienttest.ui.fragments

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.publicissapient.publicissapienttest.R
import com.publicissapient.publicissapienttest.adapters.ListBookAdapter
import com.publicissapient.publicissapienttest.models.datamodel.Book
import com.publicissapient.publicissapienttest.models.datamodel.Books
import com.publicissapient.publicissapienttest.models.datamodel.Offers
import com.publicissapient.publicissapienttest.netwroks.Resource
import com.publicissapient.publicissapienttest.netwroks.Status
import com.publicissapient.publicissapienttest.utils.Utils
import com.publicissapient.publicissapienttest.viewmodels.BasketViewModel
import kotlinx.android.synthetic.main.basket_menu.view.*
import kotlinx.android.synthetic.main.fragment_basket.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.NumberFormat
import java.util.ArrayList


class BasketFragment : Fragment() {
    private val basketViewModel: BasketViewModel by viewModel()

    private var booksList: ArrayList<Book> = ArrayList()

    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var adapter: ListBookAdapter

    val books by lazy {
        BasketFragmentArgs.fromBundle(
            requireArguments()
        ).books
    }

    private val observer = Observer<Resource<Offers>> {
        when (it.status) {
            Status.SUCCESS -> updateView(it.data!!)
            Status.ERROR -> showError(it.message!!)
            Status.LOADING -> showLoading()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.title = "Panier"
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.basket_menu, menu)

        val item: MenuItem = menu.findItem(R.id.action_close)
        item.actionView.closeBasketBtn.setOnClickListener {
            activity?.onBackPressed()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

       val view = inflater.inflate(R.layout.fragment_basket, container, false)

        basketViewModel.offer.observe(viewLifecycleOwner, observer)

        return view
    }

    private fun getOffers() {
        val ISBNs = booksList.joinToString { "${it.id}" }.replace(" ", "")
        basketViewModel.getOffers(ISBNs)
    }

    private fun setUpRecyclerView() {
        val soldBooksList: List<Book> = books.books.filter { s -> s.isSold }

        booksList.clear()
        booksList.addAll(soldBooksList)
        linearLayoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        soldBooksRecyclerView.layoutManager = linearLayoutManager
        adapter = ListBookAdapter(booksList)
        soldBooksRecyclerView.adapter = adapter
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
        getOffers()
        adapter.listener = object : ListBookAdapter.ItemClickListener {
            override fun onItemClickListener(item: Book) {
            }
        }
    }

    private fun showError(message: String) {
        spin_kit.visibility = View.GONE
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }

    private fun updateView(offers: Offers) {

        val totalPrice: Float = booksList.map { it.price }.sum()
        val newprice = Utils.applyOffer(offers, Books(booksList), totalPrice)
        val pomo = totalPrice - newprice!!
        //TODO deplace to util
        totalProducts.text = NumberFormat.getInstance().format(totalPrice).toString().plus(" €")
        totalToPay.text = NumberFormat.getInstance().format(newprice).toString().plus(" €")
        offre.text = NumberFormat.getInstance().format(pomo).toString().plus(" €")
        spin_kit.visibility = View.GONE
    }

    private fun showLoading() {
        spin_kit.visibility = View.VISIBLE
    }
}