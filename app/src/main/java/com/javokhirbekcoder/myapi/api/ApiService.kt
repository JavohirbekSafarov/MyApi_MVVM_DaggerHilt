package com.javokhirbekcoder.myapi.api

import com.javokhirbekcoder.myapi.model.APIMessage
import com.javokhirbekcoder.myapi.model.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


/*
Created by Javokhirbek on 19.08.2023 at 12:47
*/
interface ApiService {
    @POST("register")
    fun registerUser(@Body user: User): Call<APIMessage>

    @POST("login")
    fun loginUser(@Body user: User):Call<APIMessage>

    @GET("users")
    fun getAllUser(): Call<ArrayList<User>>
}