package com.aryandadhich.urja10.ui.signIn

import com.squareup.moshi.Json

data class ParticipantPostSignIn(
    @Json(name = "college_id") val collegeId: String,
    @Json(name = "password") val password: String,

)

data class ParticipantGetSignIn(
    val token: String
)

data class OrganizersPostSignIn(
    @Json(name = "login_id") val loginId: String,
    val password: String,
)

data class OrganizersGetSignIn(
    val message: String,
    val token: String = "",
    val role: String = " "
)

data class OrganizersPostSignUp(
    val role: String,
    @Json(name = "login_id") val loginId: String,
    val password: String,
    @Json(name = "college_id") val collegeId: String,
    val name: String,
    val year: String,
    val branch: String,
    @Json(name="whatsapp_country_code") val whatsappCountryCode: Int,
    @Json(name="whatsapp_number") val whatsappNumber: Double,
    @Json(name="mobile_number_country_code") val mobileNumberCountryCode: Int,
    @Json(name="mobile_number") val mobileNumber: Double
)

data class OrganizersGetSignUp(
    val message: String,
    val token: String,
    val role: String
)
