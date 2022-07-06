package com.aryandadhich.urja10.ui.games.teamGames.tennis

import com.squareup.moshi.Json

data class TennisGame(
    @Json(name = "_id") val id: String,
    var teamA: String,
    var teamB: String,
    val teamAScore: Int,
    val teamBScore: Int
)

data class PostTennisGame(
    var teamA: String,
    var teamB: String
)

data class PostTennisScoreUpdates(
    var teamAScore: Int,
    var teamBScore: Int
)
