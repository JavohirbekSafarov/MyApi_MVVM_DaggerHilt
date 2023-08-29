package com.javokhirbekcoder.myapi.ui.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.javokhirbekcoder.myapi.api.ApiService
import com.javokhirbekcoder.myapi.model.APIMessage
import com.javokhirbekcoder.myapi.model.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

/*
Created by Javokhirbek on 22.08.2023 at 19:01
*/

class RegisterFragmentRepository @Inject constructor(
    private val apiService: ApiService
) {
    fun registerUser(user: User):MutableLiveData<String>{
        val message = MutableLiveData<String>()

        apiService.registerUser(user).enqueue(object :Callback<APIMessage>{
            override fun onResponse(call: Call<APIMessage>, response: Response<APIMessage>) {
                message.postValue(response.message())
            }

            override fun onFailure(call: Call<APIMessage>, t: Throwable) {
                message.postValue("Error -> $t")
            }
        })

        return message
    }
}