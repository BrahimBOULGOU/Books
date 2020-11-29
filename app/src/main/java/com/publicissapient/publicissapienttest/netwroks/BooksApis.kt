package com.publicissapient.publicissapienttest.netwroks


import com.publicissapient.publicissapienttest.models.datamodel.Book
import com.publicissapient.publicissapienttest.models.datamodel.Offers
import retrofit2.http.GET
import retrofit2.http.Path

interface BooksApis {
    @GET("books")
    suspend fun getBooks(): List<Book>

    @GET("books/{ISBN}/commercialOffers")
    suspend fun getOffers(@Path("ISBN") ISBN: String): Offers
}