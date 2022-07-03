package com.aryandadhich.urja10.ui.games.teamGames.teams

import com.aryandadhich.urja10.ui.games.common.players.Player
import com.squareup.moshi.Json

data class Team(
    @Json(name = "_id") val teamId: String,
    val houseName: String,
    val players: List<String>
)

data class PostTeam(
    val houseName: String
)