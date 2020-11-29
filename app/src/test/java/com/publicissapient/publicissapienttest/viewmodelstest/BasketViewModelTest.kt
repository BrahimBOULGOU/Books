package com.publicissapient.publicissapienttest.viewmodelstest

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.timeout
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.publicissapient.publicissapienttest.models.datamodel.Offer
import com.publicissapient.publicissapienttest.models.datamodel.Offers
import com.publicissapient.publicissapienttest.models.repository.OfferRepository
import com.publicissapient.publicissapienttest.netwroks.Resource
import com.publicissapient.publicissapienttest.viewmodels.BasketViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Test

import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@RunWith(JUnit4::class)
class BasketViewModelTest {

    private lateinit var viewModel: BasketViewModel
    private lateinit var offerRepository: OfferRepository
    private lateinit var offerObserver: Observer<Resource<Offers>>
    private val validISBNs =
        "c8fabf68-8374-48fe-a7ea-a00ccd07afff,a460afed-e5e7-4e39-a39d-c885c05db861"
    private val invalidISBNs = ""
    private val offers = arrayListOf(
        Offer("percentage", 5),
        Offer("minus", 15),
        Offer("slice", 12, 100)
    )
    private val successResource = Resource.success(Offers(offers))
    private val errorResource = Resource.error("Not Found", null)

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @ObsoleteCoroutinesApi
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @ExperimentalCoroutinesApi
    @ObsoleteCoroutinesApi
    @Before
    fun setUp() {
        Dispatchers.setMain(mainThreadSurrogate)
        offerRepository = mock()
        runBlocking {
            whenever(offerRepository.getOffers(validISBNs)).thenReturn(successResource)
            whenever(offerRepository.getOffers(invalidISBNs)).thenReturn(errorResource)
        }
        viewModel = BasketViewModel(offerRepository)
        offerObserver = mock()
    }

    @ObsoleteCoroutinesApi
    @ExperimentalCoroutinesApi
    @After
    fun tearDown() {
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
    }

    @Test
    fun `when getOffres is called with valid ISBNs, then observer is updated with success`() =
        runBlocking {
            viewModel.offer.observeForever(offerObserver)
            viewModel.getOffers(validISBNs)
            delay(10)
            verify(offerRepository).getOffers(validISBNs)
            verify(offerObserver, timeout(50)).onChanged(Resource.loading(null))
            verify(offerObserver, timeout(50)).onChanged(successResource)
        }

    @Test
    fun `when getOffres is called with invalid ISBNs, then observer is updated with failure`() =
        runBlocking {
            viewModel.offer.observeForever(offerObserver)
            viewModel.getOffers(invalidISBNs)
            delay(10)
            verify(offerRepository).getOffers(invalidISBNs)
            verify(offerObserver, timeout(50)).onChanged(Resource.loading(null))
            verify(offerObserver, timeout(50)).onChanged(errorResource)
        }
}