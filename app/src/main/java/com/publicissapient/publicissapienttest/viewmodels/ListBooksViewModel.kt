package com.publicissapient.publicissapienttest.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.publicissapient.publicissapienttest.models.repository.BooksRepository
import com.publicissapient.publicissapienttest.netwroks.Resource
import kotlinx.coroutines.Dispatchers



class ListBooksViewModel(
    private val booksRepo: BooksRepository
) : ViewModel() {

    val book = liveData(Dispatchers.IO) {
        emit(Resource.loading(null))
        emit(booksRepo.getBooks())
    }

}