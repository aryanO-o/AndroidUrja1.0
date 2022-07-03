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
import com.aryandadhich.urja10.ui.games.common.players.Player
import com.aryandadhich.urja10.ui.games.common.players.PostPlayer
import com.aryandadhich.urja10.ui.games.teamGames.teams.PostTeam
import com.aryandadhich.urja10.ui.games.teamGames.teams.Team
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


}

object API {
    val retrofitService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}