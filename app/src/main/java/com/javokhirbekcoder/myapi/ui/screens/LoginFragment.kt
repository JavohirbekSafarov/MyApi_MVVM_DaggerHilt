package com.javokhirbekcoder.myapi.ui.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.javokhirbekcoder.myapi.R
import com.javokhirbekcoder.myapi.databinding.FragmentLoginBinding
import com.javokhirbekcoder.myapi.model.User
import com.javokhirbekcoder.myapi.ui.viewModel.LoginFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment(R.layout.fragment_login) {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private val viewModel: LoginFragmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater)

        binding.apply {

            goRegister.setOnClickListener {
                findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
            }

            submitBtn.setOnClickListener {
                if (!usernameEt.text.isNullOrEmpty() && !passwordEt.text.isNullOrEmpty()) {

                    submitBtn.text = "Loading..."
                    submitBtn.isEnabled = false

                    val user = User(
                        usernameEt.text.toString().trim(),
                        passwordEt.text.toString().trim()
                    )

                    val code = viewModel.loginUser(user)

                    code.observe(viewLifecycleOwner) {
                        val message = it.toString()

                        if (message.contains("Error")) {
                            sendToast(message)
                            submitBtn.text = "Submit"
                        } else {
                            if (message.toInt() == 404) {
                                sendToast("User Not Found")
                                submitBtn.text = "Submit"
                            } else if (message.toInt() == 4044) {
                                sendToast("Password Incorrect")
                            } else if (message.toInt() == 200) {
                                sendToast("Login Success!")
                                findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                                submitBtn.text = "Succsess"
                                viewModel.setLogged(true)
                                viewModel.setLoginData(user)
                            } else {
                                submitBtn.text = "Submit"
                                sendToast("Error Code ${message.toInt()}")
                            }
                        }
                        submitBtn.isEnabled = true
                    }
                }
            }

        }

        return binding.root
    }

    private fun sendToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}