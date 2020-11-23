package com.publicissapient.publicissapienttest.models.repository

import com.google.gson.Gson
import com.publicissapient.publicissapienttest.models.datamodel.Books
import com.publicissapient.publicissapienttest.netwroks.BooksApis
import com.publicissapient.publicissapienttest.netwroks.Resource
import com.publicissapient.publicissapienttest.netwroks.ResponseHandler
import org.koin.dsl.module


val BooksModule = module {
    factory { BooksRepository(get(), get()) }
    factory { OfferRepository(get(), get()) }
}

class BooksRepository (private val booksApis: BooksApis,
                       private val responseHandler: ResponseHandler
) {

    suspend fun getBooks(): Resource<Books> {
        return try {
            val response = booksApis.getBooks()
            val books = Books(response)
            return responseHandler.handleSuccess(books)
        } catch (e: Exception) {
            responseHandler.handleException(e)
        }
    }
}