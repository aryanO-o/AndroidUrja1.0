package com.aryandadhich.urja10.ui.games.common.gameInfo

import android.app.appsearch.StorageInfo
import com.squareup.moshi.Json

data class GameInfo(
    @Json(name = "_id") val id: String,
    val gameName: String,
    val gameTitle: String,
    @Json(name = "date_and_time") val dateAndTime: String,
    val scorer:String,
    val referee: String,
    val venue: String,
    @Json(name = "event_id") val eventId: String,
    val inGameDetails: String,
)

data class PostGameInfo(
    val gameName: String,
    val gameTitle: String,
    @Json(name = "date_and_time") val dateAndTime: String,
    val scorer:String,
    val referee: String,
    val venue: String,
    @Json(name = "event_id") val eventId: String,
    val inGameDetails: String,
)

data class GetGameInfo(
    @Json(name = "_id") val id: String,
    val gameName: String,
    val gameTitle: String,
    @Json(name = "gameInfo") val gameInfoId: String,
    @Json(name = "event_id") val eventId: String
)

data class GetGameInfoOtherDetails(
    @Json(name = "_id") val id: String,
    @Json(name = "date_and_time") val dateAndTime: String,
    val scorer: String,
    val referee: String,
    val venue: String,
    val inGameDetails:String,
)
