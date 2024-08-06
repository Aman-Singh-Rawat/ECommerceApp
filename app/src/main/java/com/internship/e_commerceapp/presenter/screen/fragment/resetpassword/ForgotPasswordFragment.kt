package com.internship.e_commerceapp.presenter.screen.fragment.resetpassword

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.internship.e_commerceapp.R
import com.internship.e_commerceapp.databinding.FragmentForgotPasswordBinding
import com.internship.e_commerceapp.presenter.screen.activity.authentication.AuthenticationActivity

class ForgotPasswordFragment : Fragment() {
    private var _binding: FragmentForgotPasswordBinding? = null
    private val binding: FragmentForgotPasswordBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentForgotPasswordBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
    }

    private fun initViews() {
        binding.apply {
            clViaSms.isSelected = true
            clViaSms.setOnClickListener { // here adding corner functionality
                clViaEmail.isSelected = false
                clViaSms.isSelected = true
            }
            clViaEmail.setOnClickListener { // here adding corner functionality
                clViaSms.isSelected = false
                clViaEmail.isSelected = true
            }
            btnContinue.setOnClickListener {
                findNavController().navigate(R.id.forgotPassOtpFragment)
            }
            imgBackPressed.setOnClickListener {
                (activity as AuthenticationActivity).onBackPressed() //Adding BackPressed here
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }
}