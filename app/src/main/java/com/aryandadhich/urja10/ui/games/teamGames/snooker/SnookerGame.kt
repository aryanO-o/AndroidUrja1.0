package com.aryandadhich.urja10.ui.games.teamGames.snooker

import com.squareup.moshi.Json

data class SnookerGame(
    @Json(name = "_id") val id: String,
    var teamA: String,
    var teamB: String,
    val teamAScore: Int,
    val teamBScore: Int
)

data class PostSnookerGame(
    var teamA: String,
    var teamB: String
)

data class PostSnookerScoreUpdates(
    var teamAScore: Int,
    var teamBScore: Int
)
