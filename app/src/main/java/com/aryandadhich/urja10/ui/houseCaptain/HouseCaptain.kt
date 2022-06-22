package com.aryandadhich.urja10.ui.houseCaptain

import com.squareup.moshi.Json

data class HouseCaptain(
    @Json(name = "login_id") val loginId: String,
    @Json(name = "college_id") val collegeId: String,
    @Json(name = "whatsapp_country_code") val whatsappCountryCode: Int,
    @Json(name = "whatsapp_number") val whatsappNumber: String,
    @Json(name = "mobile_number_country_code") val mobileCountryCode: Int,
    @Json(name = "mobile_number") val mobileNumber: String,
    val name: String,
)
