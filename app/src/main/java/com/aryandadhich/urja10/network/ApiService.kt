package com.aryandadhich.urja10.network

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
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

private const val BASE_URL = "http://192.168.137.1:8000/"

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
}

object API {
    val retrofitService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}