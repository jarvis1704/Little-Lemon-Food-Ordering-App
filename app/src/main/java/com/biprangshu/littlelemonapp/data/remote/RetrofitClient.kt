package com.biprangshu.littlelemonapp.data.remote

import com.google.firebase.appdistribution.gradle.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RetrofitClient {

    private const val  BASE_URL= "https://raw.githubusercontent.com/Meta-Mobile-Developer-PC/Working-With-Data-API/main/"


    @get:Provides
    @Singleton
    private val loggingInterceptor=HttpLoggingInterceptor().apply {
        level= HttpLoggingInterceptor.Level.BODY
    }

    @get:Provides
    @Singleton
    private val okHttpClient = OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()


    @get:Provides
    @Singleton
    val instance: LittleLemonApi by lazy {
        val retrofit= Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create(LittleLemonApi::class.java)
    }


    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): LittleLemonApi= retrofit.create(LittleLemonApi::class.java)
}