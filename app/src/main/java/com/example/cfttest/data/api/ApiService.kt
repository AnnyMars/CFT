package com.example.cfttest.data.api

import com.example.cfttest.model.CardInfo
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("/{number}")
    suspend fun getCardInfo(@Path("number")number:String): Response<CardInfo>
}