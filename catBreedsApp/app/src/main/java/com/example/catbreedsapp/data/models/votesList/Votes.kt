package com.example.catbreedsapp.data.models.votesList


import com.squareup.moshi.Json

data class Votes(
    @Json(name = "id")
    val id: Int?,
    @Json(name = "image_id")
    val imageId: String?,
    @Json(name = "sub_id")
    val subId: String?,
    @Json(name = "created_at")
    val createdAt: String?,
    @Json(name = "value")
    val value: Int?,
    @Json(name = "country_code")
    val countryCode: String?,
    @Json(name = "image")
    val image: Image?
)