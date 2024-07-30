package com.internship.e_commerceapp.presenter.screen.fragment.resetpassword

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.internship.e_commerceapp.R
import com.internship.e_commerceapp.databinding.FragmentForgotPassOtpBinding

class ForgotPassOtpFragment : Fragment() {
    private var _binding: FragmentForgotPassOtpBinding? = null
    private val binding: FragmentForgotPassOtpBinding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentForgotPassOtpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnVerify.setOnClickListener {
            findNavController().navigate(R.id.createNewPassFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }
}