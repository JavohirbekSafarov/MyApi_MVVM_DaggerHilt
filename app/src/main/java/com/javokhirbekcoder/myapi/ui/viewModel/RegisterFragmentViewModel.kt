package com.javokhirbekcoder.myapi.ui.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.javokhirbekcoder.myapi.model.User
import com.javokhirbekcoder.myapi.ui.repository.RegisterFragmentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/*
Created by Javokhirbek on 22.08.2023 at 18:59
*/

@HiltViewModel
class RegisterFragmentViewModel @Inject constructor(
    private val registerFragmentRepository: RegisterFragmentRepository
):ViewModel() {
    fun registerUser(user: User):MutableLiveData<String>{
        return registerFragmentRepository.registerUser(user)
    }
}