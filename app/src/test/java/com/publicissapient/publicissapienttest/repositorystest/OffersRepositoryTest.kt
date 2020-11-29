package com.publicissapient.publicissapienttest.repositorystest

import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.publicissapient.publicissapienttest.models.datamodel.Offer
import com.publicissapient.publicissapienttest.models.datamodel.Offers
import com.publicissapient.publicissapienttest.models.repository.OfferRepository
import com.publicissapient.publicissapienttest.netwroks.BooksApis
import com.publicissapient.publicissapienttest.netwroks.Resource
import com.publicissapient.publicissapienttest.netwroks.ResponseHandler
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.Assert.*
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mockito
import retrofit2.HttpException

@RunWith(JUnit4::class)
class OffersRepositoryTest {
    private val responseHandler = ResponseHandler()
    private lateinit var booksApis: BooksApis
    private lateinit var repository: OfferRepository
    private val validISBNs =
        "c8fabf68-8374-48fe-a7ea-a00ccd07afff,a460afed-e5e7-4e39-a39d-c885c05db861"
    private val invalidISBNs = ""

    private val offers = arrayListOf(
        Offer("percentage", 4),
        Offer("minus", 15),
        Offer("slice", 12, 100)
    )


    private val OffersResponse = Resource.success(Offers(offers))
    private val errorResponse = Resource.error("Not Found", null)

    @Before
    fun setUp() {
        booksApis = mock()
        val mockException: HttpException = mock()
        whenever(mockException.code()).thenReturn(404)
        runBlocking {
            whenever(booksApis.getOffers(invalidISBNs)).thenThrow(mockException)
            whenever(booksApis.getOffers(validISBNs)).thenReturn(Offers(offers))
        }
        repository = OfferRepository(
            booksApis,
            responseHandler
        )
    }

    @Test
    fun `test getOffres when valid ISBNs are requested, then offers is returned`() =
        runBlocking {
            assertEquals(OffersResponse.data?.offers, repository.getOffers(validISBNs).data?.offers)
        }

    @Test
    fun `test getOffres when invalid ISBNs are requested, then error is returned`() =
        runBlocking {
            assertEquals(errorResponse, repository.getOffers(invalidISBNs))
        }

}