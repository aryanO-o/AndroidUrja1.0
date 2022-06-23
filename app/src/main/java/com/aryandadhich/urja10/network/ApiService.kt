package com.aryandadhich.urja10.network

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

    @Headers("Authorization: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJyb2xlIjoic3VwZXJ2aXNvciIsImxvZ2luX2lkIjoic3VwZXJ2aXNvci0yMDIyIiwiY29sbGVnZV9pZCI6InN1cGVydmlzb3JAaWlpdG0uYWMuaW4iLCJpYXQiOjE2NTQwMTM2MTZ9.WzM8L_7oNW-uaALkcK0anJBJM63q39vwWheE0HkhZuc")
    @GET("/organizers-info/get/role-players/{rolePlayer}")
    fun getRolePlayers(@Path("rolePlayer") rolePlayer: String): Deferred<List<HouseCaptain>>

    @Headers("Authorization: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJyb2xlIjoic3VwZXJ2aXNvciIsImxvZ2luX2lkIjoic3VwZXJ2aXNvci0yMDIyIiwiY29sbGVnZV9pZCI6InN1cGVydmlzb3JAaWlpdG0uYWMuaW4iLCJpYXQiOjE2NTQwMTM2MTZ9.WzM8L_7oNW-uaALkcK0anJBJM63q39vwWheE0HkhZuc")
    @POST("organizers-auth/sign-up")
    fun addHouseCaptain(@Body postHouseCaptain: PostHouseCaptain): Deferred<OrganizersGetSignIn>

    @Headers("Authorization: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJyb2xlIjoic3VwZXJ2aXNvciIsImxvZ2luX2lkIjoic3VwZXJ2aXNvci0yMDIyIiwiY29sbGVnZV9pZCI6InN1cGVydmlzb3JAaWlpdG0uYWMuaW4iLCJpYXQiOjE2NTQwMTM2MTZ9.WzM8L_7oNW-uaALkcK0anJBJM63q39vwWheE0HkhZuc")
    @GET("/organizers-info/get/{loginId}")
    fun getRolePlayer(@Path("loginId") loginId: String): Deferred<HouseCaptain>

    @Headers("Authorization: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJyb2xlIjoic3VwZXJ2aXNvciIsImxvZ2luX2lkIjoic3VwZXJ2aXNvci0yMDIyIiwiY29sbGVnZV9pZCI6InN1cGVydmlzb3JAaWlpdG0uYWMuaW4iLCJpYXQiOjE2NTQwMTM2MTZ9.WzM8L_7oNW-uaALkcK0anJBJM63q39vwWheE0HkhZuc")
    @PUT("organizers-info/update/{loginId}")
    fun updateHouseCaptain(@Path("loginId") loginId: String, @Body postHouseCaptain: PostHouseCaptain): Deferred<OrganizersGetSignIn>

    @Headers("Authorization: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJyb2xlIjoic3VwZXJ2aXNvciIsImxvZ2luX2lkIjoic3VwZXJ2aXNvci0yMDIyIiwiY29sbGVnZV9pZCI6InN1cGVydmlzb3JAaWlpdG0uYWMuaW4iLCJpYXQiOjE2NTQwMTM2MTZ9.WzM8L_7oNW-uaALkcK0anJBJM63q39vwWheE0HkhZuc")
    @DELETE("organizers-info/delete/{loginId}")
    fun deleteHouseCaptain(@Path("loginId") loginId: String): Deferred<OrganizersGetSignIn>


}

object API {
    val retrofitService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}