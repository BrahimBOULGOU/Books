package com.publicissapient.publicissapienttest.models.datamodel

import com.google.gson.annotations.SerializedName

class Offer(
    @SerializedName("type") val type: String,
    @SerializedName("value") val myValue: Int,
    @SerializedName("sliceValue") val sliceValue: Int = 1
)