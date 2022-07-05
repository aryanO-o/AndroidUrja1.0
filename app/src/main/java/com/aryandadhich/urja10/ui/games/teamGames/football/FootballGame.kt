package com.aryandadhich.urja10.ui.games.teamGames.football

import com.squareup.moshi.Json

data class FootballGame(
    @Json(name = "_id") val id: String,
    var teamA: String,
    var teamB: String,
    val teamAScore: Int,
    val teamBScore: Int
)

data class PostFootballGame(
    var teamA: String,
    var teamB: String
)

data class PostFootballScoreUpdates(
    var teamAScore: Int,
    var teamBScore: Int
)
