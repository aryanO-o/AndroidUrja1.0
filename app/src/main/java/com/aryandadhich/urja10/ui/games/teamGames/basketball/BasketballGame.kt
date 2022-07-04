package com.aryandadhich.urja10.ui.games.teamGames.basketball

import com.squareup.moshi.Json

data class BasketballGame(
    @Json(name = "_id") val id: String,
    var teamA: String,
    var teamB: String,
    val teamAScore: Int,
    val teamBScore: Int
)