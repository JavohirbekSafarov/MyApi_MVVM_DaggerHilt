package com.javokhirbekcoder.myapi

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import com.javokhirbekcoder.myapi.databinding.ActivityMainBinding
import com.javokhirbekcoder.myapi.model.User
import com.javokhirbekcoder.myapi.ui.viewModel.LoginFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity() : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val TAG = "AAA"

    private var mlog = MutableLiveData<String>()
    private var log = ""

    private var usersList = ArrayList<User>()

    private val loginFragmentViewModel: LoginFragmentViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        mlog.observe(this) {
            log += "$it\n"
            binding.logText.text = log
        }

        val username = "JavohirbekCoder"
        val password = "Javohirbek5010"

        //registerUser(username, password)
        //getAllUser()
        //loginUser(username, password)
    }

    private fun loginUser(username: String, password: String) {
        val message = loginFragmentViewModel.loginUser(User(username, password))
        message.observe(this@MainActivity) {
            mlog.postValue(message.value)
        }

    }

//    private fun registerUser(username: String, password: String) {
//        mlog.postValue("Register user -> $username, $password")
//        val user = User(username, password)
//        val call = retrofit.registerUser(user)
//
//        Log.d(TAG, "Api Request")
//        mlog.postValue("Api Request")
//
//        call.enqueue(object : Callback<APIMessage> {
//            override fun onResponse(call: Call<APIMessage>, response: Response<APIMessage>) {
//                if (response.code() == 400) {
//                    Log.d(TAG, "Api Response -> User exists!")
//                    mlog.postValue("Api Response -> User exists!")
//                } else if (response.code() == 200) {
//                    Log.d(TAG, "Api Response -> User Created!")
//                    mlog.postValue("Api Response -> User Created!")
//                } else {
//                    Log.d(TAG, "Api Response -> Code = ${response.code()}")
//                    mlog.postValue("Api Response -> Code = ${response.code()}")
//                }
//            }
//
//            override fun onFailure(call: Call<APIMessage>, t: Throwable) {
//                Log.d(TAG, "Api Failure -> $t")
//                mlog.postValue("Api Failure -> $t")
//            }
//        })
//    }
//
//    private fun getAllUser() {
//        mlog.postValue("Api Request -> Get All User")
//
//        val call = retrofit.getAllUser()
//        call.enqueue(object : Callback<ArrayList<User>> {
//            override fun onResponse(
//                call: Call<ArrayList<User>>,
//                response: Response<ArrayList<User>>
//            ) {
//                mlog.postValue("Api Response -> Code = ${response.code()}")
//                if (response.isSuccessful) {
//                    mlog.postValue(response.body().toString())
//                    usersList = response.body()!!
//                    val adapter = UserAdapter(usersList)
//                    binding.mrecycler.adapter = adapter
//                }
//            }
//
//            override fun onFailure(call: Call<ArrayList<User>>, t: Throwable) {
//                mlog.postValue("Api Failure -> $t")
//            }
//        })
//    }
}