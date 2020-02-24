package com.alwandroid.moviecatalogue.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    var poster: Int?,
    var title: String?,
    var description: String?,
    var rating: String?,
    var date: String?
) : Parcelable