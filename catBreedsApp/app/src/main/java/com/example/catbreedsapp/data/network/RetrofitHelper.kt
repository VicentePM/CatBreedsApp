package com.example.catbreedsapp.data.network

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitHelper {

    var retrofitService: ApiService? = null

    fun getRetrofit(): ApiService {

        if (retrofitService == null) {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://api.thecatapi.com/v1/")
                .addConverterFactory(MoshiConverterFactory.create())
                .build()

            retrofitService = retrofit.create(ApiService::class.java)
        }

        return retrofitService!!
    }
}