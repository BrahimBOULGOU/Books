package com.publicissapient.publicissapienttest.models.datamodel

import com.google.gson.annotations.SerializedName

data class Book(@SerializedName("isbn") val id :String,
                @SerializedName("title") val title : String,
                @SerializedName("price") val price : Float,
                @SerializedName("cover") val image : String,
                @SerializedName("synopsis") val description: List<String>)