package com.javokhirbekcoder.myapi.ui.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.javokhirbekcoder.myapi.api.ApiService
import com.javokhirbekcoder.myapi.model.User
import com.javokhirbekcoder.myapi.ui.repository.LoginFragmentRepository
import com.javokhirbekcoder.myapi.utils.SharedPrefs
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


/*
Created by Javokhirbek on 20.08.2023 at 21:07
*/

@HiltViewModel
class LoginFragmentViewModel @Inject constructor(
    private val loginFragmentRepository: LoginFragmentRepository,
    private val sharedPrefs: SharedPrefs
) :
    ViewModel() {

    fun loginUser(user: User): MutableLiveData<String> {
        return loginFragmentRepository.loginUser(user)
    }

    fun setLogged(logged:Boolean){
        sharedPrefs.setLogged(logged)
    }

    fun setLoginData(user: User){
        sharedPrefs.setLoginData(user)
    }
}