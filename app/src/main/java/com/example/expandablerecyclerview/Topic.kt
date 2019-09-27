package com.example.expandablerecyclerview

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Topic(val id: Int, val level: Int? = null, val title: String? = null) :
    Parcelable