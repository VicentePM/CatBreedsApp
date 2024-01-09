package com.example.catbreedsapp.data.models.delVote


import com.squareup.moshi.Json

data class DelVoteResponse(
    @Json(name = "message")
    val message: String?
)