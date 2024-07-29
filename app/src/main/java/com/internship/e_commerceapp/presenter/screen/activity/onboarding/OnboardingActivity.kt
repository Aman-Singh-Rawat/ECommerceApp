package com.internship.e_commerceapp.presenter.screen.activity.onboarding

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewpager2.widget.ViewPager2
import com.internship.e_commerceapp.R
import com.internship.e_commerceapp.data.adapter.OnboardingAdapter
import com.internship.e_commerceapp.data.model.OnboardingItem
import com.internship.e_commerceapp.databinding.ActivityOnboardingBinding
import com.internship.e_commerceapp.presenter.screen.activity.authentication.AuthenticationActivity
import com.internship.e_commerceapp.util.Constant

class OnboardingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOnboardingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityOnboardingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initViews()
    }

    private fun initViews() {
        binding.viewPager.adapter = OnboardingAdapter(Constant.viewPagerItemsList(this))
        binding.viewPager.clipToPadding = false
        binding.viewPager.clipChildren = false
        binding.viewPager.getChildAt(0).overScrollMode = View.OVER_SCROLL_NEVER

        binding.dotsIndicator.attachTo(binding.viewPager)

        onPageChangeCallback()
    }

    private fun onPageChangeCallback() {
        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)

                when(position) {
                    0 -> {
                        binding.btnNext.setOnClickListener {
                            binding.viewPager.setCurrentItem(1, true)
                        }
                    }
                    1 -> {
                        binding.btnNext.setOnClickListener {
                            binding.viewPager.setCurrentItem(2, true)
                        }
                    }
                    2 -> {
                        binding.btnNext.setOnClickListener {
                            startActivity(
                                Intent(this@OnboardingActivity,
                                AuthenticationActivity::class.java)
                            )
                        }
                    }
                }
            }
        })
    }
}