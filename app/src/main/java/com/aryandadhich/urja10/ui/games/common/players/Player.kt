package com.aryandadhich.urja10.ui.games.common.players

import com.squareup.moshi.Json

data class Player(
    @Json(name = "_id") val id: String,
    @Json(name = "jerseyNo")val jerseyNo: Int,
    @Json(name = "name")val name: String,
)

data class PostPlayer(
    val jerseyNo: Int,
    val name: String
)
