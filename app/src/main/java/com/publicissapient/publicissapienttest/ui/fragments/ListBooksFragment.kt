package com.publicissapient.publicissapienttest.ui.fragments

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.publicissapient.publicissapienttest.R
import com.publicissapient.publicissapienttest.adapters.ListBookAdapter
import com.publicissapient.publicissapienttest.models.datamodel.Book
import com.publicissapient.publicissapienttest.models.datamodel.Books
import com.publicissapient.publicissapienttest.netwroks.Resource
import com.publicissapient.publicissapienttest.netwroks.Status
import com.publicissapient.publicissapienttest.viewmodels.ListBooksViewModel
import kotlinx.android.synthetic.main.custom_menu.view.*
import kotlinx.android.synthetic.main.fragment_list_books.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*


class ListBooksFragment : Fragment() {

    private var booksList: ArrayList<Book> = ArrayList()
    private val listBooksViewModel: ListBooksViewModel by viewModel()

    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var adapter: ListBookAdapter

    private val observer = Observer<Resource<Books>> {
        when (it.status) {
            Status.SUCCESS -> updateList(it.data!!)
            Status.ERROR -> showError(it.message!!)
            Status.LOADING -> showLoading()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.title = "Books"
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.fragment_list_books, container, false)

        listBooksViewModel.book.observe(viewLifecycleOwner, observer)
        return view
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.backet_menu, menu)

        val item: MenuItem = menu.findItem(R.id.action_favorite)
        item.actionView.imageView3.setOnClickListener {
            navigateToBasket()
        }
        val soldBooksList: List<Book> = booksList.filter { s -> s.isSold }
        item.actionView.textView11.text = soldBooksList.size.toString()
    }

    private fun navigateToBasket() {
        val action =
            ListBooksFragmentDirections.actionListBooksFragmentToBasketFragment(
                books = Books(booksList)
            )

        findNavController().navigate(action)
    }

    private fun setUpRecyclerView(){
        linearLayoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

       booksRecycler.layoutManager = linearLayoutManager

        adapter = ListBookAdapter(booksList)
        booksRecycler.adapter = adapter
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpRecyclerView()
        adapter.listener = object : ListBookAdapter.ItemClickListener {
            override fun onItemClickListener(item: Book) {
                val action =
                    ListBooksFragmentDirections.actionListBooksFragmentToDetailsFragment(
                        book = item
                    )
                findNavController().navigate(action)
            }
        }
    }

    private fun showError(message: String) {
        spinKit.visibility = View.GONE
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }

    private fun updateList(books: Books) {
        spinKit.visibility = View.GONE
        booksList.clear()
        booksList.addAll(books.books)
        adapter.notifyItemInserted(booksList.size - 1)
    }

    private fun showLoading() {
        spinKit.visibility = View.VISIBLE
    }
}