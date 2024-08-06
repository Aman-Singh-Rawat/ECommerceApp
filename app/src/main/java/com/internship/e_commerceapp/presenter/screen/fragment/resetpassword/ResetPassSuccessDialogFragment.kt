package com.internship.e_commerceapp.presenter.screen.fragment.resetpassword

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.DialogFragment
import com.internship.e_commerceapp.R
import com.internship.e_commerceapp.databinding.FragmentResetPassSuccessDialogBinding
import com.internship.e_commerceapp.presenter.screen.activity.home.MainActivity
import com.internship.e_commerceapp.presenter.screen.activity.authentication.AuthenticationActivity
import com.internship.e_commerceapp.util.Constant

class ResetPassSuccessDialogFragment : DialogFragment() {
    private val fragment: String by lazy { arguments?.getString(Constant.DIALOG_FRAGMENT) ?: "" }
    private var _binding: FragmentResetPassSuccessDialogBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentResetPassSuccessDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()

    }

    private fun initViews() {
        if (fragment == Constant.FILL_PROFILE) {
            binding.imgCongratulation.setImageResource(R.drawable.ic_profile_signin)
        } else {
            binding.imgCongratulation.setImageResource(R.drawable.img_shield)
        }

        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(
                requireContext(),
                MainActivity::class.java
            ).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
            startActivity(intent)
            (activity as AuthenticationActivity).finish()
        }, 2000)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return dialog
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }
}