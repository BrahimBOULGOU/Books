package com.publicissapient.publicissapienttest.utils

import com.publicissapient.publicissapienttest.models.datamodel.Books
import com.publicissapient.publicissapienttest.models.datamodel.Offer
import com.publicissapient.publicissapienttest.models.datamodel.Offers
import kotlin.math.roundToInt

object Utils {
    fun applyOffer(offers: Offers, basket: Books, total: Float): Float? {
        val listsize = offers.offers.size - 1
        val newPrices: MutableList<Float> = ArrayList()

        for (i in 0..listsize) {
            when (offers.offers.get(i).type) {
                "percentage" -> newPrices.add(
                    getNewPriceByPercentage(
                        offers.offers.get(i),
                        basket,
                        total
                    )
                )
                "minus" -> newPrices.add(getNewPriceByMinus(offers.offers.get(i), total))
                "slice" -> newPrices.add(getNewPriceBySlice(offers.offers.get(i), total))
            }
        }
        return newPrices.min()
    }

    private fun getNewPriceByPercentage(offer: Offer, basket: Books, total: Float): Float =
        total - (basket.books.size * offer.myValue)

    private fun getNewPriceByMinus(offer: Offer, total: Float): Float = total - offer.myValue

    private fun getNewPriceBySlice(offer: Offer, total: Float): Float =
        total - ((total / offer.sliceValue).roundToInt() * offer.myValue)

}

