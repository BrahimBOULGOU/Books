package com.publicissapient.publicissapienttest.utils

import com.publicissapient.publicissapienttest.models.datamodel.Books
import com.publicissapient.publicissapienttest.models.datamodel.Offer
import com.publicissapient.publicissapienttest.models.datamodel.Offers
import kotlin.math.roundToInt


fun applyOffer(offers : Offers, basket: Books, total : Float) : Float?{
    val listsize = offers.offers.size - 1
    var newPrices : MutableList<Float> = ArrayList()

    for (i in 0..listsize) {
        when (offers.offers.get(i).type) {
            "percentage" -> newPrices.add(getNewPriceByPercentage(offers.offers.get(i), basket, total))
            "minus" -> newPrices.add(getNewPriceByMinus(offers.offers.get(i), basket, total))
            "slice" ->  newPrices.add(getNewPriceBySlice(offers.offers.get(i), basket, total))
        }
    }
    return newPrices.min()
}

fun getNewPriceByPercentage(offer : Offer, basket: Books, total : Float) : Float = total - (basket.books.size * offer.myValue)

fun getNewPriceByMinus(offer : Offer, basket: Books, total : Float): Float = total - offer.myValue

fun getNewPriceBySlice(offer : Offer, basket: Books, total : Float): Float = total - ((total/100).roundToInt()*offer.myValue)
