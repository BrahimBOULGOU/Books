package com.publicissapient.publicissapienttest.models.datamodel

import com.google.gson.annotations.SerializedName
import com.squareup.moshi.JsonClass

//TODO remove jsonclass if not nec
@JsonClass(generateAdapter = true)
class Offer(@SerializedName("type") val type : String, @SerializedName("value") val myValue : Int, @SerializedName("sliceValue") val sliceValue : Int = 100)