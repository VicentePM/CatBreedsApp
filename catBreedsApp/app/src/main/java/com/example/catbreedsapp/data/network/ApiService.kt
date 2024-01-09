package com.example.catbreedsapp.data.network

import com.example.catbreedsapp.data.models.breedList.Breed
import com.example.catbreedsapp.data.models.delVote.DelVoteResponse
import com.example.catbreedsapp.data.models.sendVotes.ResponseVote
import com.example.catbreedsapp.data.models.sendVotes.SendVotes
import com.example.catbreedsapp.data.models.votesList.Votes
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @Headers("x-api-key: live_e4aKPoj9sjHzCw4AFBZOz05cWIjaU630cHIBI4oUH2Bp4vIKTAc8jUpkJIosxU1G")
    @GET("breeds")
    suspend fun getBreeds(): Response<List<Breed>>

    @Headers(
        "x-api-key:  live_e4aKPoj9sjHzCw4AFBZOz05cWIjaU630cHIBI4oUH2Bp4vIKTAc8jUpkJIosxU1G",
        "Content-Type: application/json"
    )
    @POST("votes")
    suspend fun sendVote(@Body vote: SendVotes): Response<ResponseVote>

    @Headers("x-api-key: live_e4aKPoj9sjHzCw4AFBZOz05cWIjaU630cHIBI4oUH2Bp4vIKTAc8jUpkJIosxU1G")
    @GET("votes")
    suspend fun getVotesList(@Query("sub_id") user: String): Response<List<Votes>>

    @Headers("x-api-key: live_e4aKPoj9sjHzCw4AFBZOz05cWIjaU630cHIBI4oUH2Bp4vIKTAc8jUpkJIosxU1G")
    @DELETE("votes/{id}")
    suspend fun delVote(@Path("id") id: String): Response<DelVoteResponse>
}