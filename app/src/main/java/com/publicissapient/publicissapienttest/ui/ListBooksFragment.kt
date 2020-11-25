package com.publicissapient.publicissapienttest.ui

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.publicissapient.publicissapienttest.R
import com.publicissapient.publicissapienttest.adapters.ListBookAdapter
import com.publicissapient.publicissapienttest.databinding.FragmentListBooksBinding
import com.publicissapient.publicissapienttest.models.datamodel.Book
import com.publicissapient.publicissapienttest.models.datamodel.Books
import com.publicissapient.publicissapienttest.netwroks.Resource
import com.publicissapient.publicissapienttest.netwroks.Status
import com.publicissapient.publicissapienttest.viewmodels.ListBooksViewModel
import kotlinx.android.synthetic.main.custom_menu.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*


class ListBooksFragment : Fragment() {

    private var booksList: ArrayList<Book> = ArrayList()
    private val listBooksViewModel: ListBooksViewModel by viewModel()
    private lateinit var binding: FragmentListBooksBinding

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
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_list_books, container, false)
        linearLayoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        binding.booksRecycler.layoutManager = linearLayoutManager
        binding.viewModel = listBooksViewModel
        adapter = ListBookAdapter(booksList)
        binding.booksRecycler.adapter = adapter
        listBooksViewModel.book.observe(viewLifecycleOwner, observer)

        return binding.root
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.backet_menu, menu)

        val item: MenuItem = menu.findItem(R.id.action_favorite)
        item.actionView.imageView3.setOnClickListener {
            navigateAbout()
        }
        item.actionView.textView11.text = "3"
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }

    private fun navigateAbout() {
        val action = ListBooksFragmentDirections.actionListBooksFragmentToBasketFragment(books = Books(booksList))
        findNavController().navigate(action)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter.listener = object : ListBookAdapter.ItemClickListener {
            override fun onItemClickListener(item: Book) {
                val action = ListBooksFragmentDirections.actionListBooksFragmentToDetailsFragment(book = item)
                findNavController().navigate(action)
            }
        }

    }
    private fun showError(message: String) {
        Log.d("Mydta", "error"+message)
        binding.spinKit.visibility = View.GONE
    }

    private fun updateList(books : Books ){
        binding.spinKit.visibility = View.GONE
        // For Test
        books.books.get(0).isSold = true
        books.books.get(1).isSold = true
        books.books.get(2).isSold = true

        booksList.addAll(books.books)
        adapter.notifyItemInserted(booksList.size-1)
    }

    private fun showLoading() {
        Log.d("Mydta", "loading")
        binding.spinKit.visibility = View.VISIBLE
    }
}