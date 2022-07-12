package com.aryandadhich.urja10.ui.games.individualGames.chess

import com.squareup.moshi.Json

data class ChessGame(
    @Json(name = "_id") val id: String,
    val playerA: String,
    val playerB: String,
    val winner: String
)

data class PostChessGame(
    val playerA: String,
    val playerB: String,
    val winner: String
)
