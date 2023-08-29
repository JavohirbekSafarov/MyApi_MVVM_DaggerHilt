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
import com.javokhirbekcoder.myapi.databinding.FragmentRegisterBinding
import com.javokhirbekcoder.myapi.model.User
import com.javokhirbekcoder.myapi.ui.viewModel.RegisterFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : Fragment(R.layout.fragment_register) {

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    private val viewModel: RegisterFragmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisterBinding.inflate(inflater)

        binding.apply {

            goLogin.setOnClickListener {
                findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
            }

            submitBtn.setOnClickListener {
                if (!usernameEt.text.isNullOrEmpty() && !passwordEt.text.isNullOrEmpty()) {

                    submitBtn.text = "Loading..."
                    submitBtn.isEnabled = false

                    val message = viewModel.registerUser(
                        User(
                            usernameEt.text.toString().trim(),
                            passwordEt.text.toString().trim()
                        )
                    )

                    message.observe(viewLifecycleOwner) {
                        sendToast(it.toString())
                        if (it.toString() == "CREATED") {
                            submitBtn.text = "Success"
                            goLogin.callOnClick()
                        }else{
                            submitBtn.text = "Submit"
                            submitBtn.isEnabled = true
                        }
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