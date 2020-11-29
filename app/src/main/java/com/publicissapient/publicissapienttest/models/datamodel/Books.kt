package com.publicissapient.publicissapienttest.models.datamodel

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Books(val books: List<Book>) : Parcelable