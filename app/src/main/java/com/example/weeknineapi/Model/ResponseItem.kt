package com.example.weeknineapi.Model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

// data class for API posts
@Parcelize
data class ResponseItem(
    val body: String,
    val id: Int,
    val title: String,
    val userId: Int
): Parcelable