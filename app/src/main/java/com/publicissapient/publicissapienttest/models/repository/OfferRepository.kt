package com.publicissapient.publicissapienttest.models.repository

import com.google.gson.JsonObject
import com.publicissapient.publicissapienttest.models.datamodel.Books
import com.publicissapient.publicissapienttest.models.datamodel.Offers
import com.publicissapient.publicissapienttest.netwroks.BooksApis
import com.publicissapient.publicissapienttest.netwroks.Resource
import com.publicissapient.publicissapienttest.netwroks.ResponseHandler

class OfferRepository(
    private val booksApis: BooksApis,
    private val responseHandler: ResponseHandler
) {

    suspend fun getOffers(ISBNs: String): Resource<Offers> {
        return try {
            val response = booksApis.getOffers(ISBNs)
            return responseHandler.handleSuccess(response)
        } catch (e: Exception) {
            responseHandler.handleException(e)
        }
    }
}