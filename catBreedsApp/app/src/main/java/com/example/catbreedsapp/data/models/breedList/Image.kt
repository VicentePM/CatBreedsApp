package com.example.catbreedsapp.data.models.breedList


import com.squareup.moshi.Json

data class Image(
    @field:Json(name = "id")
    val id: String?,
    @field:Json(name = "width")
    val width: Int?,
    @field:Json(name = "height")
    val height: Int?,
    @field:Json(name = "url")
    val url: String?
)