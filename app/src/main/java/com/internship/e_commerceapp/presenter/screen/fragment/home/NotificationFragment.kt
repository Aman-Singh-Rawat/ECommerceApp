package com.internship.e_commerceapp.presenter.screen.fragment.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.internship.e_commerceapp.R
import com.internship.e_commerceapp.data.adapter.NotificationParentAdapter
import com.internship.e_commerceapp.data.model.NotificationChild
import com.internship.e_commerceapp.data.model.NotificationParent
import com.internship.e_commerceapp.databinding.FragmentNotificationBinding
import com.internship.e_commerceapp.presenter.screen.activity.home.MainActivity

class NotificationFragment : Fragment() {
    private var _binding: FragmentNotificationBinding? = null
    private val binding: FragmentNotificationBinding get() = _binding!!
    private val notificationAdapter = NotificationParentAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNotificationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
    }

    private fun initViews() {
        binding.rvNotificationParent.adapter = notificationAdapter
        notificationAdapter.updateUi(dummyList())

        binding.imgBackPressed.setOnClickListener {
            (activity as MainActivity).onBackPressed()
        }
    }

    private fun dummyList(): MutableList<NotificationParent> {
        return mutableListOf(
            NotificationParent(
                "Today",
                mutableListOf(
                    NotificationChild(
                        R.drawable.ic_home,
                        "30% Special Discount!",
                        "Special promotion only valid today"
                    ),
                ),
                ),
            NotificationParent(
                "Yesterday",
                mutableListOf(
                    NotificationChild(
                        R.drawable.ic_wallet,
                        "Top Up E-Wallet Successful!",
                        "You have to top up your e-wallet"
                    ),
                    NotificationChild(
                        R.drawable.ic_apple,
                        "New Services Available!",
                        "Now you can track orders in real time"
                    ),
                ),
            ),
            NotificationParent(
                "August 04, 2024",
                mutableListOf(
                    NotificationChild(
                        R.drawable.ic_order,
                        "Credit Card Connected!",
                        "Credit Card has been linked!"
                    ),
                    NotificationChild(
                        R.drawable.ic_user,
                        "Account Setup Successful!",
                        "Your account has been created!"
                    ),
                ),
            )
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }
}