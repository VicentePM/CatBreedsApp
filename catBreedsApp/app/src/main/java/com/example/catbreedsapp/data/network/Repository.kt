package com.example.catbreedsapp.data.network

import android.content.Context
import com.example.catbreedsapp.data.models.sendVotes.SendVotes

class Repository(val context: Context) {

    private val retrofit = RetrofitHelper.getRetrofit()
    suspend fun getBreeds() = retrofit.getBreeds()
    suspend fun voteBreed(vote: SendVotes) = retrofit.sendVote(vote)
    suspend fun getVoteList(user: String) = retrofit.getVotesList(user)
    suspend fun delVote(id: String) = retrofit.delVote(id)
}