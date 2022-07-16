package com.aryandadhich.urja10.ui.home

import com.squareup.moshi.Json

data class Notice(
    @Json(name = "_id") val id:String,
    val heading:String,
    val message: String,
    val imageURL:String,
)

data class PostNotice(
    val heading: String,
    val message: String,
    val imageURL: String
)