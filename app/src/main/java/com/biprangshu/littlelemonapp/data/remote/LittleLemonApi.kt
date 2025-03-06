package com.biprangshu.littlelemonapp.data.remote

import okhttp3.Call
import retrofit2.Response
import retrofit2.http.GET

interface LittleLemonApi {

    @GET("menu.json")
    suspend fun getMenu(): Response<com.biprangshu.littlelemonapp.data.remote.Response>
}