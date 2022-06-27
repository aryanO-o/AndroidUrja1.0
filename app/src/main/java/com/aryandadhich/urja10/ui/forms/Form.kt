package com.aryandadhich.urja10.ui.forms

import com.squareup.moshi.Json

data class Form(
    @Json(name = "id") val id: Int,
    @Json(name = "to_select") val toSelect: String,
    @Json(name = "is_active") val isActive: Boolean
)

data class PostForm(
    @Json(name = "to_select") val toSelect: String,
    @Json(name = "is_active") val isActive: Boolean
)

data class FillForm(
    @Json(name = "college_id") val collegeId: String,
    val branch: String,
    val year: Int,
    @Json(name = "whatsapp_country_code") val whatsappCountryCode: Int,
    @Json(name = "whatsapp_number") val whatsappNumber: String,
    @Json(name = "mobile_number_country_code") val mobileCountryCode: Int,
    @Json(name = "mobile_number") val mobileNumber: String,
    val name: String,
)

data class ApplyForm(
    @Json(name = "college_id") val collegeId: String,
)