package com.javokhirbekcoder.myapi.ui.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.javokhirbekcoder.myapi.model.User
import com.javokhirbekcoder.myapi.ui.repository.HomeFragmentRepository
import com.javokhirbekcoder.myapi.utils.SharedPrefs
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/*
Created by Javokhirbek on 23.08.2023 at 8:20
*/

@HiltViewModel
class HomeFragmentViewModel @Inject constructor(
    private val sharedPrefs: SharedPrefs,
    private val homeFragmentRepository: HomeFragmentRepository
) :ViewModel() {

    fun getLoginData() = sharedPrefs.getLoginData()

    fun getLogged() = sharedPrefs.getLogged()

    fun setLogged(logged:Boolean){
        sharedPrefs.setLogged(logged)
    }

    fun deleteLoginData(){
        sharedPrefs.setLoginData(User("",""))
    }

}