package com.aryandadhich.urja10.ui.games.teamGames.volleyball

import com.squareup.moshi.Json

data class VolleyballGame(
    @Json(name = "_id") val id: String,
    var teamA: String,
    var teamB: String,
    val teamAScore: Int,
    val teamBScore: Int
)

data class PostVolleyballGame(
    var teamA: String,
    var teamB: String
)

data class PostVolleyballScoreUpdates(
    var teamAScore: Int,
    var teamBScore: Int
)
