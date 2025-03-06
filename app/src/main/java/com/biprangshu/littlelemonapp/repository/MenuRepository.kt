package com.biprangshu.littlelemonapp.repository

import com.biprangshu.littlelemonapp.data.remote.LittleLemonApi
import com.biprangshu.littlelemonapp.data.remote.Menu
import com.biprangshu.littlelemonapp.data.remote.Response
import com.biprangshu.littlelemonapp.data.remote.RetrofitClient
import okhttp3.Callback
import javax.inject.Inject

class MenuRepository @Inject constructor(
    private val apiService: LittleLemonApi
) {


    suspend fun fetchMenu(): retrofit2.Response<Response> = apiService.getMenu()
}