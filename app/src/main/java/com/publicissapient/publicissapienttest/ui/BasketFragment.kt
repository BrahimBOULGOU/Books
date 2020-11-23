package com.publicissapient.publicissapienttest.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.google.gson.JsonObject
import com.publicissapient.publicissapienttest.R
import com.publicissapient.publicissapienttest.databinding.FragmentBasketBinding
import com.publicissapient.publicissapienttest.models.datamodel.Offers
import com.publicissapient.publicissapienttest.netwroks.Resource
import com.publicissapient.publicissapienttest.netwroks.Status
import com.publicissapient.publicissapienttest.viewmodels.BasketViewModel
import org.json.JSONObject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.dsl.module

val viewFragment = module {
    factory { ListBooksFragment() }
    factory { BasketFragment() }
}

class BasketFragment : Fragment() {
    private val basketViewModel: BasketViewModel by viewModel()
    private lateinit var binding: FragmentBasketBinding

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
        basketViewModel.offer.observe(viewLifecycleOwner, observer)
        basketViewModel.getoffers("c8fabf68-8374-48fe-a7ea-a00ccd07afff,a460afed-e5e7-4e39-a39d-c885c05db861")
        return binding.root
    }

    private fun showError(message: String) {
        Log.d("Mydta", "error"+message)
    }

    private fun updateView(offers: Offers){
        Log.d("Mydta", "data"+offers.offers.get(0).type)
    }

    private fun showLoading() {
        Log.d("Mydta", "loading")
    }
}