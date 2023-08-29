package com.javokhirbekcoder.myapi.ui.screens

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.javokhirbekcoder.myapi.R
import com.javokhirbekcoder.myapi.databinding.FragmentSplashBinding
import com.javokhirbekcoder.myapi.ui.viewModel.SplashFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment : Fragment(R.layout.fragment_splash) {


    private var _binding: FragmentSplashBinding? = null
    private val binding get() = _binding!!

//    private val TAG = "AAA"

    private var isInternetConnected: Boolean = false

    private val viewModel: SplashFragmentViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentSplashBinding.bind(view)

        viewModel.getInternetConnection().observe(viewLifecycleOwner) {
            isInternetConnected = it

            if (it) {
                mainAction()
            } else {
                sendToast("Please connect to internet!")
            }
        }
    }

    private fun mainAction() {
        Handler(Looper.getMainLooper()).postDelayed({

            if (isInternetConnected) {

                if (viewModel.getLogged()) {
                    val user = viewModel.getLoginData()

                    if (user != null) {

                        val code = viewModel.checkLogin(user)

                        code.observe(viewLifecycleOwner) {
                            val message = it.toString()

                            if (message.contains("Error")) {
                                sendToast(message)
                            } else {
                                if (message.toInt() == 404) {
                                    sendToast("User Not Found")
                                    goLogin()
                                } else if (message.toInt() == 4044) {
                                    sendToast("Password Incorrect")
                                    goLogin()
                                } else if (message.toInt() == 200) {
//                                All correct
                                    findNavController().navigate(R.id.action_splashFragment_to_homeFragment)
                                } else {
                                    sendToast("Error Code ${message.toInt()}")
                                    goLogin()
                                }
                            }
                        }

                    }
                } else {
                    sendToast("Not Logged, Please Login!")
                    goLogin()
                }
            } else
                sendToast("Please connect to internet!")
        }, 1000)
    }

    private fun sendToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }

    private fun goLogin() {
        findNavController().navigate(R.id.action_splashFragment_to_loginFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}