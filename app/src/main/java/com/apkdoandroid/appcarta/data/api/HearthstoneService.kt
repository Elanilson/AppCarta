package com.apkdoandroid.appcarta.data.api

import com.apkdoandroid.appcarta.data.model.Carta
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface HearthstoneService {
    @GET("cards/sets/{conjunto}")
    suspend fun recuperarCartoes(@Path("conjunto") conjunto : String) : Response<List<Carta>>
}