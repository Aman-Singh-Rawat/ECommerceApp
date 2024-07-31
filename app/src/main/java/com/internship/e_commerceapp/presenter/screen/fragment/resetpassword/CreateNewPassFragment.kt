package com.internship.e_commerceapp.presenter.screen.fragment.resetpassword

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.internship.e_commerceapp.databinding.FragmentCreateNewPassBinding

class CreateNewPassFragment : Fragment() {
    private val dialogFragment = ResetPassSuccessDialogFragment()
    private var _binding: FragmentCreateNewPassBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCreateNewPassBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnContinue.setOnClickListener {
            dialogFragment.show(parentFragmentManager, "dialogFragment")
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}