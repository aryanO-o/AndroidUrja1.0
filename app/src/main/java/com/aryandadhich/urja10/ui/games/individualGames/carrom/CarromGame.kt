package com.aryandadhich.urja10.ui.games.individualGames.carrom

import com.squareup.moshi.Json

data class CarromGame(
    @Json(name = "_id") val id: String,
      val playerA: String,
      val playerB: String,
      val winner: String
)

data class PostCarromGame(
    val playerA: String,
    val playerB: String,
    val winner: String
)