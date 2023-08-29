package com.javokhirbekcoder.myapi.ui.screens

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.javokhirbekcoder.myapi.R
import com.javokhirbekcoder.myapi.databinding.FragmentHomeBinding
import com.javokhirbekcoder.myapi.ui.viewModel.HomeFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewmodel: HomeFragmentViewModel by viewModels()

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = FragmentHomeBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)


        if (viewmodel.getLogged()) {
            val user = viewmodel.getLoginData()
            if (user != null) {
                binding.loginDataTv.text = user.username + " | " + user.password
            }
        }else{
            sendToast("Not Logged, Please Login!")
        }

        binding.apply {
            logout.setOnClickListener {
                viewmodel.setLogged(false)
                viewmodel.deleteLoginData()
                findNavController().navigate(R.id.action_homeFragment_to_loginFragment)
            }
        }
    }

    private fun sendToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}