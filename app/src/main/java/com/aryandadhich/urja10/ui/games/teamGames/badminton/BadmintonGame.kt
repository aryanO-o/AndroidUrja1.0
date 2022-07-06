package com.aryandadhich.urja10.ui.games.teamGames.badminton

import com.squareup.moshi.Json

class BadmintonGame(
    @Json(name = "_id") val id: String,
    var teamA: String,
    var teamB: String,
    val teamAScore: Int,
    val teamBScore: Int
)

data class PostBadmintonGame(
    var teamA: String,
    var teamB: String
)

data class PostBadmintonScoreUpdates(
    var teamAScore: Int,
    var teamBScore: Int
)
