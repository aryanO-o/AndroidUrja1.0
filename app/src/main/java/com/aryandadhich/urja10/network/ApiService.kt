package com.aryandadhich.urja10.network

import android.util.Log
import com.aryandadhich.urja10.ui.coordinator.Coordinator
import com.aryandadhich.urja10.ui.coordinator.PostCoordinator
import com.aryandadhich.urja10.ui.eventCoordinator.EventCoordinator
import com.aryandadhich.urja10.ui.eventCoordinator.PostEventCoordinator
import com.aryandadhich.urja10.ui.forms.ApplyForm
import com.aryandadhich.urja10.ui.forms.FillForm
import com.aryandadhich.urja10.ui.forms.Form
import com.aryandadhich.urja10.ui.forms.PostForm
import com.aryandadhich.urja10.ui.games.common.gameInfo.GameInfo
import com.aryandadhich.urja10.ui.games.common.gameInfo.GetGameInfo
import com.aryandadhich.urja10.ui.games.common.gameInfo.GetGameInfoOtherDetails
import com.aryandadhich.urja10.ui.games.common.gameInfo.PostGameInfo
import com.aryandadhich.urja10.ui.games.common.players.Player
import com.aryandadhich.urja10.ui.games.common.players.PostPlayer
import com.aryandadhich.urja10.ui.games.teamGames.badminton.BadmintonGame
import com.aryandadhich.urja10.ui.games.teamGames.badminton.PostBadmintonGame
import com.aryandadhich.urja10.ui.games.teamGames.badminton.PostBadmintonScoreUpdates
import com.aryandadhich.urja10.ui.games.teamGames.basketball.BasketballGame
import com.aryandadhich.urja10.ui.games.teamGames.basketball.PostBasketballGame
import com.aryandadhich.urja10.ui.games.teamGames.basketball.PostBasketballScoreUpdates
import com.aryandadhich.urja10.ui.games.teamGames.football.FootballGame
import com.aryandadhich.urja10.ui.games.teamGames.football.PostFootballGame
import com.aryandadhich.urja10.ui.games.teamGames.football.PostFootballScoreUpdates
import com.aryandadhich.urja10.ui.games.teamGames.squash.PostSquashGame
import com.aryandadhich.urja10.ui.games.teamGames.squash.PostSquashScoreUpdates
import com.aryandadhich.urja10.ui.games.teamGames.squash.SquashGame
import com.aryandadhich.urja10.ui.games.teamGames.tableTennis.PostTableTennisGame
import com.aryandadhich.urja10.ui.games.teamGames.tableTennis.PostTableTennisScoreUpdates
import com.aryandadhich.urja10.ui.games.teamGames.tableTennis.TableTennisGame
import com.aryandadhich.urja10.ui.games.teamGames.teams.PostTeam
import com.aryandadhich.urja10.ui.games.teamGames.teams.Team
import com.aryandadhich.urja10.ui.games.teamGames.tennis.PostTennisGame
import com.aryandadhich.urja10.ui.games.teamGames.tennis.PostTennisScoreUpdates
import com.aryandadhich.urja10.ui.games.teamGames.tennis.TennisGame
import com.aryandadhich.urja10.ui.houseCaptain.HouseCaptain
import com.aryandadhich.urja10.ui.houseCaptain.PostHouseCaptain
import com.aryandadhich.urja10.ui.signIn.OrganizersGetSignIn
import com.aryandadhich.urja10.ui.signIn.OrganizersGetSignUp
import com.aryandadhich.urja10.ui.signIn.OrganizersPostSignIn
import com.aryandadhich.urja10.ui.signIn.OrganizersPostSignUp
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*

private const val BASE_URL = "http://192.168.65.243:8000/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .build()

interface ApiService {

    @POST("organizers-auth/sign-up")
    fun organizersSignUp(@Body organizersPostSignUp: OrganizersPostSignUp): Deferred<OrganizersGetSignUp>

    @POST("organizers-auth/sign-in")
    fun organizersSignIn(@Body organizersPostSignIn: OrganizersPostSignIn): Deferred<OrganizersGetSignIn>




    // house captain function calls

    @Headers("Authorization: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJyb2xlIjoic3VwZXJ2aXNvciIsImxvZ2luX2lkIjoic3VwZXJ2aXNvci0yMDIyIiwiY29sbGVnZV9pZCI6InN1cGVydmlzb3JAaWlpdG0uYWMuaW4iLCJpYXQiOjE2NTQwMTM2MTZ9.WzM8L_7oNW-uaALkcK0anJBJM63q39vwWheE0HkhZuc")
    @GET("/organizers-info/get/role-players/{rolePlayer}")
    fun getRolePlayers(@Path("rolePlayer") rolePlayer: String): Deferred<List<HouseCaptain>>

    @Headers("Authorization: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJyb2xlIjoic3VwZXJ2aXNvciIsImxvZ2luX2lkIjoic3VwZXJ2aXNvci0yMDIyIiwiY29sbGVnZV9pZCI6InN1cGVydmlzb3JAaWlpdG0uYWMuaW4iLCJpYXQiOjE2NTQwMTM2MTZ9.WzM8L_7oNW-uaALkcK0anJBJM63q39vwWheE0HkhZuc")
    @GET("/organizers-info/get/{loginId}")
    fun getRolePlayer(@Path("loginId") loginId: String): Deferred<HouseCaptain>

    @Headers("Authorization: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJyb2xlIjoic3VwZXJ2aXNvciIsImxvZ2luX2lkIjoic3VwZXJ2aXNvci0yMDIyIiwiY29sbGVnZV9pZCI6InN1cGVydmlzb3JAaWlpdG0uYWMuaW4iLCJpYXQiOjE2NTQwMTM2MTZ9.WzM8L_7oNW-uaALkcK0anJBJM63q39vwWheE0HkhZuc")
    @POST("organizers-auth/sign-up")
    fun addHouseCaptain(@Body postHouseCaptain: PostHouseCaptain): Deferred<OrganizersGetSignIn>

    @Headers("Authorization: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJyb2xlIjoic3VwZXJ2aXNvciIsImxvZ2luX2lkIjoic3VwZXJ2aXNvci0yMDIyIiwiY29sbGVnZV9pZCI6InN1cGVydmlzb3JAaWlpdG0uYWMuaW4iLCJpYXQiOjE2NTQwMTM2MTZ9.WzM8L_7oNW-uaALkcK0anJBJM63q39vwWheE0HkhZuc")
    @PUT("organizers-info/update/{loginId}")
    fun updateHouseCaptain(@Path("loginId") loginId: String, @Body postHouseCaptain: PostHouseCaptain): Deferred<OrganizersGetSignIn>

    @Headers("Authorization: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJyb2xlIjoic3VwZXJ2aXNvciIsImxvZ2luX2lkIjoic3VwZXJ2aXNvci0yMDIyIiwiY29sbGVnZV9pZCI6InN1cGVydmlzb3JAaWlpdG0uYWMuaW4iLCJpYXQiOjE2NTQwMTM2MTZ9.WzM8L_7oNW-uaALkcK0anJBJM63q39vwWheE0HkhZuc")
    @DELETE("organizers-info/delete/{loginId}")
    fun deleteHouseCaptain(@Path("loginId") loginId: String): Deferred<OrganizersGetSignIn>

    // coordinator function calls

    @Headers("Authorization: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJyb2xlIjoic3VwZXJ2aXNvciIsImxvZ2luX2lkIjoic3VwZXJ2aXNvci0yMDIyIiwiY29sbGVnZV9pZCI6InN1cGVydmlzb3JAaWlpdG0uYWMuaW4iLCJpYXQiOjE2NTQwMTM2MTZ9.WzM8L_7oNW-uaALkcK0anJBJM63q39vwWheE0HkhZuc")
    @GET("/organizers-info/get/role-players/{rolePlayer}")
    fun getCoordinators(@Path("rolePlayer") rolePlayer: String): Deferred<List<Coordinator>>

    @Headers("Authorization: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJyb2xlIjoic3VwZXJ2aXNvciIsImxvZ2luX2lkIjoic3VwZXJ2aXNvci0yMDIyIiwiY29sbGVnZV9pZCI6InN1cGVydmlzb3JAaWlpdG0uYWMuaW4iLCJpYXQiOjE2NTQwMTM2MTZ9.WzM8L_7oNW-uaALkcK0anJBJM63q39vwWheE0HkhZuc")
    @POST("organizers-auth/sign-up")
    fun addCoordinator(@Body postCoordinator: PostCoordinator): Deferred<OrganizersGetSignIn>

    @Headers("Authorization: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJyb2xlIjoic3VwZXJ2aXNvciIsImxvZ2luX2lkIjoic3VwZXJ2aXNvci0yMDIyIiwiY29sbGVnZV9pZCI6InN1cGVydmlzb3JAaWlpdG0uYWMuaW4iLCJpYXQiOjE2NTQwMTM2MTZ9.WzM8L_7oNW-uaALkcK0anJBJM63q39vwWheE0HkhZuc")
    @PUT("organizers-info/update/{loginId}")
    fun updateCoordinator(@Path("loginId") loginId: String, @Body postCoordinator: PostCoordinator): Deferred<OrganizersGetSignIn>

    @Headers("Authorization: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJyb2xlIjoic3VwZXJ2aXNvciIsImxvZ2luX2lkIjoic3VwZXJ2aXNvci0yMDIyIiwiY29sbGVnZV9pZCI6InN1cGVydmlzb3JAaWlpdG0uYWMuaW4iLCJpYXQiOjE2NTQwMTM2MTZ9.WzM8L_7oNW-uaALkcK0anJBJM63q39vwWheE0HkhZuc")
    @DELETE("organizers-info/delete/{loginId}")
    fun deleteCoordinator(@Path("loginId") loginId: String): Deferred<OrganizersGetSignIn>

    @Headers("Authorization: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJyb2xlIjoic3VwZXJ2aXNvciIsImxvZ2luX2lkIjoic3VwZXJ2aXNvci0yMDIyIiwiY29sbGVnZV9pZCI6InN1cGVydmlzb3JAaWlpdG0uYWMuaW4iLCJpYXQiOjE2NTQwMTM2MTZ9.WzM8L_7oNW-uaALkcK0anJBJM63q39vwWheE0HkhZuc")
    @GET("/organizers-info/get/{loginId}")
    fun getCoordinator(@Path("loginId") loginId: String): Deferred<Coordinator>

    // coordinator function calls

    @Headers("Authorization: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJyb2xlIjoic3VwZXJ2aXNvciIsImxvZ2luX2lkIjoic3VwZXJ2aXNvci0yMDIyIiwiY29sbGVnZV9pZCI6InN1cGVydmlzb3JAaWlpdG0uYWMuaW4iLCJpYXQiOjE2NTQwMTM2MTZ9.WzM8L_7oNW-uaALkcK0anJBJM63q39vwWheE0HkhZuc")
    @GET("/organizers-info/get/role-players/{rolePlayer}")
    fun getEventCoordinators(@Path("rolePlayer") rolePlayer: String): Deferred<List<EventCoordinator>>

    @Headers("Authorization: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJyb2xlIjoic3VwZXJ2aXNvciIsImxvZ2luX2lkIjoic3VwZXJ2aXNvci0yMDIyIiwiY29sbGVnZV9pZCI6InN1cGVydmlzb3JAaWlpdG0uYWMuaW4iLCJpYXQiOjE2NTQwMTM2MTZ9.WzM8L_7oNW-uaALkcK0anJBJM63q39vwWheE0HkhZuc")
    @POST("organizers-auth/sign-up")
    fun addEventCoordinator(@Body postEventCoordinator: PostEventCoordinator): Deferred<OrganizersGetSignIn>

    @Headers("Authorization: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJyb2xlIjoic3VwZXJ2aXNvciIsImxvZ2luX2lkIjoic3VwZXJ2aXNvci0yMDIyIiwiY29sbGVnZV9pZCI6InN1cGVydmlzb3JAaWlpdG0uYWMuaW4iLCJpYXQiOjE2NTQwMTM2MTZ9.WzM8L_7oNW-uaALkcK0anJBJM63q39vwWheE0HkhZuc")
    @PUT("organizers-info/update/{loginId}")
    fun updateEventCoordinator(@Path("loginId") loginId: String, @Body postEventCoordinator: PostEventCoordinator): Deferred<OrganizersGetSignIn>

    @Headers("Authorization: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJyb2xlIjoic3VwZXJ2aXNvciIsImxvZ2luX2lkIjoic3VwZXJ2aXNvci0yMDIyIiwiY29sbGVnZV9pZCI6InN1cGVydmlzb3JAaWlpdG0uYWMuaW4iLCJpYXQiOjE2NTQwMTM2MTZ9.WzM8L_7oNW-uaALkcK0anJBJM63q39vwWheE0HkhZuc")
    @DELETE("organizers-info/delete/{loginId}")
    fun deleteEventCoordinator(@Path("loginId") loginId: String): Deferred<OrganizersGetSignIn>

    @Headers("Authorization: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJyb2xlIjoic3VwZXJ2aXNvciIsImxvZ2luX2lkIjoic3VwZXJ2aXNvci0yMDIyIiwiY29sbGVnZV9pZCI6InN1cGVydmlzb3JAaWlpdG0uYWMuaW4iLCJpYXQiOjE2NTQwMTM2MTZ9.WzM8L_7oNW-uaALkcK0anJBJM63q39vwWheE0HkhZuc")
    @GET("/organizers-info/get/{loginId}")
    fun getEventCoordinator(@Path("loginId") loginId: String): Deferred<EventCoordinator>

    // forms function calls

    @GET("/forms/get-all-forms")
    fun getAllForms(): Deferred<List<Form>>

    @Headers("Authorization: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJyb2xlIjoic3VwZXJ2aXNvciIsImxvZ2luX2lkIjoic3VwZXJ2aXNvci0yMDIyIiwiY29sbGVnZV9pZCI6InN1cGVydmlzb3JAaWlpdG0uYWMuaW4iLCJpYXQiOjE2NTQwMTM2MTZ9.WzM8L_7oNW-uaALkcK0anJBJM63q39vwWheE0HkhZuc")
    @POST("/forms/update/{formId}")
    fun toggleFormIsActive(@Path("formId") formId: Int, @Body postForm: PostForm): Deferred<OrganizersGetSignIn>

    @Headers("Authorization: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJyb2xlIjoic3VwZXJ2aXNvciIsImxvZ2luX2lkIjoic3VwZXJ2aXNvci0yMDIyIiwiY29sbGVnZV9pZCI6InN1cGVydmlzb3JAaWlpdG0uYWMuaW4iLCJpYXQiOjE2NTQwMTM2MTZ9.WzM8L_7oNW-uaALkcK0anJBJM63q39vwWheE0HkhZuc")
    @POST("/forms/create")
    fun addForm(@Body postForm: PostForm): Deferred<OrganizersGetSignIn>

    @Headers("Authorization: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJyb2xlIjoic3VwZXJ2aXNvciIsImxvZ2luX2lkIjoic3VwZXJ2aXNvci0yMDIyIiwiY29sbGVnZV9pZCI6InN1cGVydmlzb3JAaWlpdG0uYWMuaW4iLCJpYXQiOjE2NTQwMTM2MTZ9.WzM8L_7oNW-uaALkcK0anJBJM63q39vwWheE0HkhZuc")
    @DELETE("/forms/delete/{formId}")
    fun deleteForm(@Path("formId") formId: Int): Deferred<OrganizersGetSignIn>

    @POST("/participants-info/insert/{collegeId}")
    fun insertDataInParticipantsInfo(@Path("collegeId") collegeId: String, @Body fillForm: FillForm): Deferred<OrganizersGetSignIn>

    @POST("/forms/apply/{formId}")
    fun applyToForm( @Path("formId") formId: Int, @Body applyForm: ApplyForm) :Deferred<OrganizersGetSignIn>

    @GET("/participants-info/get/{collegeId}")
    fun getParticipantsInfo(@Path("collegeId") collegeId: String): Deferred<String>

    @GET("/forms/get-applications/{formId}")
    fun getFilledForms(@Path("formId") formId: Int): Deferred<List<FillForm>>

    // game player functions
    @Headers("Authorization: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJyb2xlIjoic3VwZXJ2aXNvciIsImxvZ2luX2lkIjoic3VwZXJ2aXNvci0yMDIyIiwiY29sbGVnZV9pZCI6InN1cGVydmlzb3JAaWlpdG0uYWMuaW4iLCJpYXQiOjE2NTQwMTM2MTZ9.WzM8L_7oNW-uaALkcK0anJBJM63q39vwWheE0HkhZuc")
    @GET("/games/teams/get/all-players/{teamId}")
    fun getAllGamePlayers(@Path("teamId") teamId: String): Deferred<List<Player>>

    @Headers("Authorization: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJyb2xlIjoic3VwZXJ2aXNvciIsImxvZ2luX2lkIjoic3VwZXJ2aXNvci0yMDIyIiwiY29sbGVnZV9pZCI6InN1cGVydmlzb3JAaWlpdG0uYWMuaW4iLCJpYXQiOjE2NTQwMTM2MTZ9.WzM8L_7oNW-uaALkcK0anJBJM63q39vwWheE0HkhZuc")
    @PUT("/games/teams/remove/player/{teamId}/{playerId}")
    fun deleteGamePlayer(@Path("teamId") teamId: String, @Path("playerId") playerId: String): Deferred<Player>

    @Headers("Authorization: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJyb2xlIjoic3VwZXJ2aXNvciIsImxvZ2luX2lkIjoic3VwZXJ2aXNvci0yMDIyIiwiY29sbGVnZV9pZCI6InN1cGVydmlzb3JAaWlpdG0uYWMuaW4iLCJpYXQiOjE2NTQwMTM2MTZ9.WzM8L_7oNW-uaALkcK0anJBJM63q39vwWheE0HkhZuc")
    @PUT("/games/teams/add/player/{teamId}")
    fun addGamePlayer(@Path("teamId") teamId: String, @Body postPlayer: PostPlayer): Deferred<Team>

    // team game teams functions
    @Headers("Authorization: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJyb2xlIjoic3VwZXJ2aXNvciIsImxvZ2luX2lkIjoic3VwZXJ2aXNvci0yMDIyIiwiY29sbGVnZV9pZCI6InN1cGVydmlzb3JAaWlpdG0uYWMuaW4iLCJpYXQiOjE2NTQwMTM2MTZ9.WzM8L_7oNW-uaALkcK0anJBJM63q39vwWheE0HkhZuc")
    @GET("/games/teams/get/all-teams")
    fun getAllTeams(): Deferred<List<Team>>

    @Headers("Authorization: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJyb2xlIjoic3VwZXJ2aXNvciIsImxvZ2luX2lkIjoic3VwZXJ2aXNvci0yMDIyIiwiY29sbGVnZV9pZCI6InN1cGVydmlzb3JAaWlpdG0uYWMuaW4iLCJpYXQiOjE2NTQwMTM2MTZ9.WzM8L_7oNW-uaALkcK0anJBJM63q39vwWheE0HkhZuc")
    @POST("/games/teams/add")
    fun addTeams(@Body postTeam: PostTeam): Deferred<Team>

    @Headers("Authorization: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJyb2xlIjoic3VwZXJ2aXNvciIsImxvZ2luX2lkIjoic3VwZXJ2aXNvci0yMDIyIiwiY29sbGVnZV9pZCI6InN1cGVydmlzb3JAaWlpdG0uYWMuaW4iLCJpYXQiOjE2NTQwMTM2MTZ9.WzM8L_7oNW-uaALkcK0anJBJM63q39vwWheE0HkhZuc")
    @DELETE("games/teams/remove/team/{teamId}")
    fun deleteTeam(@Path("teamId") teamId: String): Deferred<Team>

    @Headers("Authorization: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJyb2xlIjoic3VwZXJ2aXNvciIsImxvZ2luX2lkIjoic3VwZXJ2aXNvci0yMDIyIiwiY29sbGVnZV9pZCI6InN1cGVydmlzb3JAaWlpdG0uYWMuaW4iLCJpYXQiOjE2NTQwMTM2MTZ9.WzM8L_7oNW-uaALkcK0anJBJM63q39vwWheE0HkhZuc")
    @GET("/games/teams/get/team-by-id/{teamId}")
    fun getTeamById(@Path("teamId") teamId: String): Deferred<Team?>

    // game info functions
    @Headers("Authorization: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJyb2xlIjoic3VwZXJ2aXNvciIsImxvZ2luX2lkIjoic3VwZXJ2aXNvci0yMDIyIiwiY29sbGVnZV9pZCI6InN1cGVydmlzb3JAaWlpdG0uYWMuaW4iLCJpYXQiOjE2NTQwMTM2MTZ9.WzM8L_7oNW-uaALkcK0anJBJM63q39vwWheE0HkhZuc")
    @GET("/games/get/game-by-event-id/{eventId}")
    fun getGameDetails(@Path("eventId") eventId: String): Deferred<GetGameInfo>

    @Headers("Authorization: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJyb2xlIjoic3VwZXJ2aXNvciIsImxvZ2luX2lkIjoic3VwZXJ2aXNvci0yMDIyIiwiY29sbGVnZV9pZCI6InN1cGVydmlzb3JAaWlpdG0uYWMuaW4iLCJpYXQiOjE2NTQwMTM2MTZ9.WzM8L_7oNW-uaALkcK0anJBJM63q39vwWheE0HkhZuc")
    @POST("/games/add")
    fun createGameDetails(@Body postGameInfo: PostGameInfo): Deferred<GetGameInfo>

    @Headers("Authorization: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJyb2xlIjoic3VwZXJ2aXNvciIsImxvZ2luX2lkIjoic3VwZXJ2aXNvci0yMDIyIiwiY29sbGVnZV9pZCI6InN1cGVydmlzb3JAaWlpdG0uYWMuaW4iLCJpYXQiOjE2NTQwMTM2MTZ9.WzM8L_7oNW-uaALkcK0anJBJM63q39vwWheE0HkhZuc")
    @GET("/games/game-info/get/{gameInfoId}")
    fun getGameInfoOtherDetails(@Path("gameInfoId") gameInfoId: String): Deferred<GetGameInfoOtherDetails>

    @Headers("Authorization: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJyb2xlIjoic3VwZXJ2aXNvciIsImxvZ2luX2lkIjoic3VwZXJ2aXNvci0yMDIyIiwiY29sbGVnZV9pZCI6InN1cGVydmlzb3JAaWlpdG0uYWMuaW4iLCJpYXQiOjE2NTQwMTM2MTZ9.WzM8L_7oNW-uaALkcK0anJBJM63q39vwWheE0HkhZuc")
    @PUT("/games/update/{gameInfoId}")
    fun updateGameDetails(@Path("gameInfoId") gameInfoId: String, @Body gameInfo: PostGameInfo): Deferred<GetGameInfo>

    // basketball game functions
    @Headers("Authorization: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJyb2xlIjoic3VwZXJ2aXNvciIsImxvZ2luX2lkIjoic3VwZXJ2aXNvci0yMDIyIiwiY29sbGVnZV9pZCI6InN1cGVydmlzb3JAaWlpdG0uYWMuaW4iLCJpYXQiOjE2NTQwMTM2MTZ9.WzM8L_7oNW-uaALkcK0anJBJM63q39vwWheE0HkhZuc")
    @GET("/games/basketball/get/all")
    fun getAllBasketballGames(): Deferred<List<BasketballGame>>

    @Headers("Authorization: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJyb2xlIjoic3VwZXJ2aXNvciIsImxvZ2luX2lkIjoic3VwZXJ2aXNvci0yMDIyIiwiY29sbGVnZV9pZCI6InN1cGVydmlzb3JAaWlpdG0uYWMuaW4iLCJpYXQiOjE2NTQwMTM2MTZ9.WzM8L_7oNW-uaALkcK0anJBJM63q39vwWheE0HkhZuc")
    @POST("/games/basketball/add")
    fun addBasketballGame(@Body postBasketballGame: PostBasketballGame): Deferred<BasketballGame>

    @Headers("Authorization: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJyb2xlIjoic3VwZXJ2aXNvciIsImxvZ2luX2lkIjoic3VwZXJ2aXNvci0yMDIyIiwiY29sbGVnZV9pZCI6InN1cGVydmlzb3JAaWlpdG0uYWMuaW4iLCJpYXQiOjE2NTQwMTM2MTZ9.WzM8L_7oNW-uaALkcK0anJBJM63q39vwWheE0HkhZuc")
    @GET("/games/basketball/get/game-by-id/{eventId}")
    fun getBasketballGameById(@Path("eventId") eventId: String): Deferred<BasketballGame>

    @Headers("Authorization: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJyb2xlIjoic3VwZXJ2aXNvciIsImxvZ2luX2lkIjoic3VwZXJ2aXNvci0yMDIyIiwiY29sbGVnZV9pZCI6InN1cGVydmlzb3JAaWlpdG0uYWMuaW4iLCJpYXQiOjE2NTQwMTM2MTZ9.WzM8L_7oNW-uaALkcK0anJBJM63q39vwWheE0HkhZuc")
    @POST("/games/basketball/set-winner/{eventId}")
    fun updateBasketballGame(@Path("eventId") eventId: String, @Body postBasketballScoreUpdates: PostBasketballScoreUpdates): Deferred<BasketballGame>

    // football game functions
    @Headers("Authorization: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJyb2xlIjoic3VwZXJ2aXNvciIsImxvZ2luX2lkIjoic3VwZXJ2aXNvci0yMDIyIiwiY29sbGVnZV9pZCI6InN1cGVydmlzb3JAaWlpdG0uYWMuaW4iLCJpYXQiOjE2NTQwMTM2MTZ9.WzM8L_7oNW-uaALkcK0anJBJM63q39vwWheE0HkhZuc")
    @GET("/games/football/get/all")
    fun getAllFootballGames(): Deferred<List<FootballGame>>

    @Headers("Authorization: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJyb2xlIjoic3VwZXJ2aXNvciIsImxvZ2luX2lkIjoic3VwZXJ2aXNvci0yMDIyIiwiY29sbGVnZV9pZCI6InN1cGVydmlzb3JAaWlpdG0uYWMuaW4iLCJpYXQiOjE2NTQwMTM2MTZ9.WzM8L_7oNW-uaALkcK0anJBJM63q39vwWheE0HkhZuc")
    @POST("/games/football/add")
    fun addFootballGame(@Body postFootballGame: PostFootballGame): Deferred<FootballGame>

    @Headers("Authorization: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJyb2xlIjoic3VwZXJ2aXNvciIsImxvZ2luX2lkIjoic3VwZXJ2aXNvci0yMDIyIiwiY29sbGVnZV9pZCI6InN1cGVydmlzb3JAaWlpdG0uYWMuaW4iLCJpYXQiOjE2NTQwMTM2MTZ9.WzM8L_7oNW-uaALkcK0anJBJM63q39vwWheE0HkhZuc")
    @GET("/games/football/get/game-by-id/{eventId}")
    fun getFootballGameById(@Path("eventId") eventId: String): Deferred<FootballGame>

    @Headers("Authorization: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJyb2xlIjoic3VwZXJ2aXNvciIsImxvZ2luX2lkIjoic3VwZXJ2aXNvci0yMDIyIiwiY29sbGVnZV9pZCI6InN1cGVydmlzb3JAaWlpdG0uYWMuaW4iLCJpYXQiOjE2NTQwMTM2MTZ9.WzM8L_7oNW-uaALkcK0anJBJM63q39vwWheE0HkhZuc")
    @POST("/games/football/set-winner/{eventId}")
    fun updateFootballGame(@Path("eventId") eventId: String, @Body postFootballScoreUpdates: PostFootballScoreUpdates): Deferred<FootballGame>


    // badminton game functions
    @Headers("Authorization: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJyb2xlIjoic3VwZXJ2aXNvciIsImxvZ2luX2lkIjoic3VwZXJ2aXNvci0yMDIyIiwiY29sbGVnZV9pZCI6InN1cGVydmlzb3JAaWlpdG0uYWMuaW4iLCJpYXQiOjE2NTQwMTM2MTZ9.WzM8L_7oNW-uaALkcK0anJBJM63q39vwWheE0HkhZuc")
    @GET("/games/badminton/get/all")
    fun getAllBadmintonGames(): Deferred<List<BadmintonGame>>

    @Headers("Authorization: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJyb2xlIjoic3VwZXJ2aXNvciIsImxvZ2luX2lkIjoic3VwZXJ2aXNvci0yMDIyIiwiY29sbGVnZV9pZCI6InN1cGVydmlzb3JAaWlpdG0uYWMuaW4iLCJpYXQiOjE2NTQwMTM2MTZ9.WzM8L_7oNW-uaALkcK0anJBJM63q39vwWheE0HkhZuc")
    @POST("/games/badminton/add")
    fun addBadmintonGame(@Body postBadmintonGame: PostBadmintonGame): Deferred<BadmintonGame>

    @Headers("Authorization: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJyb2xlIjoic3VwZXJ2aXNvciIsImxvZ2luX2lkIjoic3VwZXJ2aXNvci0yMDIyIiwiY29sbGVnZV9pZCI6InN1cGVydmlzb3JAaWlpdG0uYWMuaW4iLCJpYXQiOjE2NTQwMTM2MTZ9.WzM8L_7oNW-uaALkcK0anJBJM63q39vwWheE0HkhZuc")
    @GET("/games/badminton/get/game-by-id/{eventId}")
    fun getBadmintonGameById(@Path("eventId") eventId: String): Deferred<BadmintonGame>

    @Headers("Authorization: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJyb2xlIjoiY29vcmRpbmF0b3IiLCJsb2dpbl9pZCI6ImNvb3JkaW5hdG9yLTEtMjAyMiIsImNvbGxlZ2VfaWQiOiJiY3NfMjAyMDAxNEBpaWl0bS5hYy5pbiIsImlhdCI6MTY1NDQ3NzMxM30.p9D_YRwMS-d9jVsYVu6G2Ez4k8FMn5hwYYlOe_mnTNE")
    @PUT("/games/badminton/set-winner/{eventId}")
    fun updateBadmintonGame(@Path("eventId") eventId: String, @Body postBadmintonScoreUpdates: PostBadmintonScoreUpdates): Deferred<BadmintonGame>


    // tennis game functions
    @Headers("Authorization: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJyb2xlIjoic3VwZXJ2aXNvciIsImxvZ2luX2lkIjoic3VwZXJ2aXNvci0yMDIyIiwiY29sbGVnZV9pZCI6InN1cGVydmlzb3JAaWlpdG0uYWMuaW4iLCJpYXQiOjE2NTQwMTM2MTZ9.WzM8L_7oNW-uaALkcK0anJBJM63q39vwWheE0HkhZuc")
    @GET("/games/lawn-tennis/get/all")
    fun getAllTennisGames(): Deferred<List<TennisGame>>

    @Headers("Authorization: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJyb2xlIjoic3VwZXJ2aXNvciIsImxvZ2luX2lkIjoic3VwZXJ2aXNvci0yMDIyIiwiY29sbGVnZV9pZCI6InN1cGVydmlzb3JAaWlpdG0uYWMuaW4iLCJpYXQiOjE2NTQwMTM2MTZ9.WzM8L_7oNW-uaALkcK0anJBJM63q39vwWheE0HkhZuc")
    @POST("/games/lawn-tennis/add")
    fun addTennisGame(@Body postTennisGame: PostTennisGame): Deferred<TennisGame>


    @Headers("Authorization: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJyb2xlIjoic3VwZXJ2aXNvciIsImxvZ2luX2lkIjoic3VwZXJ2aXNvci0yMDIyIiwiY29sbGVnZV9pZCI6InN1cGVydmlzb3JAaWlpdG0uYWMuaW4iLCJpYXQiOjE2NTQwMTM2MTZ9.WzM8L_7oNW-uaALkcK0anJBJM63q39vwWheE0HkhZuc")
    @GET("/games/lawn-tennis/get/game-by-id/{eventId}")
    fun getTennisGameById(@Path("eventId") eventId: String): Deferred<TennisGame>

    @Headers("Authorization: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJyb2xlIjoiY29vcmRpbmF0b3IiLCJsb2dpbl9pZCI6ImNvb3JkaW5hdG9yLTEtMjAyMiIsImNvbGxlZ2VfaWQiOiJiY3NfMjAyMDAxNEBpaWl0bS5hYy5pbiIsImlhdCI6MTY1NDQ3NzMxM30.p9D_YRwMS-d9jVsYVu6G2Ez4k8FMn5hwYYlOe_mnTNE")
    @PUT("/games/lawn-tennis/set-winner/{eventId}")
    fun updateTennisGame(@Path("eventId") eventId: String, @Body postTennisScoreUpdates: PostTennisScoreUpdates): Deferred<TennisGame>

    // table tennis game functions
    @Headers("Authorization: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJyb2xlIjoic3VwZXJ2aXNvciIsImxvZ2luX2lkIjoic3VwZXJ2aXNvci0yMDIyIiwiY29sbGVnZV9pZCI6InN1cGVydmlzb3JAaWlpdG0uYWMuaW4iLCJpYXQiOjE2NTQwMTM2MTZ9.WzM8L_7oNW-uaALkcK0anJBJM63q39vwWheE0HkhZuc")
    @GET("/games/table-tennis/get/all")
    fun getAllTableTennisGames(): Deferred<List<TableTennisGame>>

    @Headers("Authorization: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJyb2xlIjoic3VwZXJ2aXNvciIsImxvZ2luX2lkIjoic3VwZXJ2aXNvci0yMDIyIiwiY29sbGVnZV9pZCI6InN1cGVydmlzb3JAaWlpdG0uYWMuaW4iLCJpYXQiOjE2NTQwMTM2MTZ9.WzM8L_7oNW-uaALkcK0anJBJM63q39vwWheE0HkhZuc")
    @POST("/games/table-tennis/add")
    fun addTableTennisGame(@Body postTableTennisGame: PostTableTennisGame): Deferred<TableTennisGame>

    @Headers("Authorization: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJyb2xlIjoic3VwZXJ2aXNvciIsImxvZ2luX2lkIjoic3VwZXJ2aXNvci0yMDIyIiwiY29sbGVnZV9pZCI6InN1cGVydmlzb3JAaWlpdG0uYWMuaW4iLCJpYXQiOjE2NTQwMTM2MTZ9.WzM8L_7oNW-uaALkcK0anJBJM63q39vwWheE0HkhZuc")
    @GET("/games/table-tennis/get/game-by-id/{eventId}")
    fun getTableTennisGameById(@Path("eventId") eventId: String): Deferred<TableTennisGame>

    @Headers("Authorization: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJyb2xlIjoiY29vcmRpbmF0b3IiLCJsb2dpbl9pZCI6ImNvb3JkaW5hdG9yLTEtMjAyMiIsImNvbGxlZ2VfaWQiOiJiY3NfMjAyMDAxNEBpaWl0bS5hYy5pbiIsImlhdCI6MTY1NDQ3NzMxM30.p9D_YRwMS-d9jVsYVu6G2Ez4k8FMn5hwYYlOe_mnTNE")
    @PUT("/games/table-tennis/set-winner/{eventId}")
    fun updateTableTennisGame(@Path("eventId") eventId: String, @Body postTableTennisScoreUpdates: PostTableTennisScoreUpdates): Deferred<TableTennisGame>

    // squash game functions
    @Headers("Authorization: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJyb2xlIjoic3VwZXJ2aXNvciIsImxvZ2luX2lkIjoic3VwZXJ2aXNvci0yMDIyIiwiY29sbGVnZV9pZCI6InN1cGVydmlzb3JAaWlpdG0uYWMuaW4iLCJpYXQiOjE2NTQwMTM2MTZ9.WzM8L_7oNW-uaALkcK0anJBJM63q39vwWheE0HkhZuc")
    @GET("/games/squash/get/all")
    fun getAllSquashGames(): Deferred<List<SquashGame>>

    @Headers("Authorization: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJyb2xlIjoic3VwZXJ2aXNvciIsImxvZ2luX2lkIjoic3VwZXJ2aXNvci0yMDIyIiwiY29sbGVnZV9pZCI6InN1cGVydmlzb3JAaWlpdG0uYWMuaW4iLCJpYXQiOjE2NTQwMTM2MTZ9.WzM8L_7oNW-uaALkcK0anJBJM63q39vwWheE0HkhZuc")
    @POST("/games/squash/add")
    fun addSquashGame(@Body postSquashGame: PostSquashGame): Deferred<SquashGame>

    @Headers("Authorization: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJyb2xlIjoic3VwZXJ2aXNvciIsImxvZ2luX2lkIjoic3VwZXJ2aXNvci0yMDIyIiwiY29sbGVnZV9pZCI6InN1cGVydmlzb3JAaWlpdG0uYWMuaW4iLCJpYXQiOjE2NTQwMTM2MTZ9.WzM8L_7oNW-uaALkcK0anJBJM63q39vwWheE0HkhZuc")
    @GET("/games/squash/get/game-by-id/{eventId}")
    fun getSquashGameById(@Path("eventId") eventId: String): Deferred<SquashGame>

    @Headers("Authorization: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJyb2xlIjoiY29vcmRpbmF0b3IiLCJsb2dpbl9pZCI6ImNvb3JkaW5hdG9yLTEtMjAyMiIsImNvbGxlZ2VfaWQiOiJiY3NfMjAyMDAxNEBpaWl0bS5hYy5pbiIsImlhdCI6MTY1NDQ3NzMxM30.p9D_YRwMS-d9jVsYVu6G2Ez4k8FMn5hwYYlOe_mnTNE")
    @PUT("/games/squash/set-winner/{eventId}")
    fun updateSquashGame(@Path("eventId") eventId: String, @Body postSquashScoreUpdates: PostSquashScoreUpdates): Deferred<SquashGame>

}

object API {
    val retrofitService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}