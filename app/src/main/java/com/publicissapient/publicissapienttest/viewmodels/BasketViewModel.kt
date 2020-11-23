package com.publicissapient.publicissapienttest.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import com.publicissapient.publicissapienttest.models.repository.OfferRepository
import com.publicissapient.publicissapienttest.netwroks.Resource
import kotlinx.coroutines.Dispatchers



class BasketViewModel (private val offerRepo: OfferRepository): ViewModel() {

    private val ISBNs = MutableLiveData<String>()

    fun getoffers(input: String) {
        ISBNs.value = input
    }

    val offer = ISBNs.switchMap { ISBNs ->
        liveData(Dispatchers.IO) {
            emit(Resource.loading(null))
            emit(offerRepo.getOffers(ISBNs))
        }
    }
}
