package com.aryandadhich.urja10.ui.games.teamGames.cricket

import com.squareup.moshi.Json

data class CricketGame(
    @Json(name = "_id") val id: String,
    var teamA: String,
    var teamB: String,
    val teamAScore: String,
    val teamBScore: String
)

data class PostCricketGame(
    var teamA: String,
    var teamB: String
)

data class PostCricketScoreUpdates(
    var teamAScore: String,
    var teamBScore: String
)
