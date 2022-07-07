package com.aryandadhich.urja10.ui.games.teamGames.squash

import com.squareup.moshi.Json

data class SquashGame(
    @Json(name = "_id") val id: String,
    var teamA: String,
    var teamB: String,
    val teamAScore: Int,
    val teamBScore: Int
)

data class PostSquashGame(
    var teamA: String,
    var teamB: String
)

data class PostSquashScoreUpdates(
    var teamAScore: Int,
    var teamBScore: Int
)
