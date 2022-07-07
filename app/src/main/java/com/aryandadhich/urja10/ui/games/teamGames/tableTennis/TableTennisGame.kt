package com.aryandadhich.urja10.ui.games.teamGames.tableTennis

import com.squareup.moshi.Json

data class TableTennisGame(
    @Json(name = "_id") val id: String,
    var teamA: String,
    var teamB: String,
    val teamAScore: Int,
    val teamBScore: Int
)

data class PostTableTennisGame(
    var teamA: String,
    var teamB: String
)

data class PostTableTennisScoreUpdates(
    var teamAScore: Int,
    var teamBScore: Int
)