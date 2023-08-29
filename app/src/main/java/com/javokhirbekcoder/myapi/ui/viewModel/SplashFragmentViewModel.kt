package com.javokhirbekcoder.myapi.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.javokhirbekcoder.myapi.model.User
import com.javokhirbekcoder.myapi.ui.repository.SplashFragmentRepository
import com.javokhirbekcoder.myapi.utils.NetworkStateListener
import com.javokhirbekcoder.myapi.utils.SharedPrefs
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


/*
Created by Javokhirbek on 20.08.2023 at 21:01
*/

@HiltViewModel
class SplashFragmentViewModel @Inject constructor(
    private val sharedPrefs: SharedPrefs,
    private val splashFragmentRepository: SplashFragmentRepository,
    private val networkStateListener: NetworkStateListener
) : ViewModel() {

    fun getLoginData() = sharedPrefs.getLoginData()

    fun getLogged() = sharedPrefs.getLogged()

    fun checkLogin(user: User): MutableLiveData<String> {
        return splashFragmentRepository.checkLogin(user)
    }

    fun getInternetConnection() : LiveData<Boolean> {
        return networkStateListener
    }
}