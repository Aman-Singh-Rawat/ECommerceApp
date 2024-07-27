package com.internship.e_commerceapp.data.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.internship.e_commerceapp.data.model.OnboardingItem
import com.internship.e_commerceapp.databinding.OnboardingViewPagerItemLayoutBinding

class OnboardingAdapter(private val onboardingList: List<OnboardingItem>):
    RecyclerView.Adapter<OnboardingAdapter.OnboardingViewHolder>() {
    inner class OnboardingViewHolder(val binding: OnboardingViewPagerItemLayoutBinding): RecyclerView.ViewHolder(binding.root) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnboardingViewHolder {
        val binding = OnboardingViewPagerItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return OnboardingViewHolder(binding)
    }

    override fun getItemCount(): Int = onboardingList.size

    override fun onBindViewHolder(holder: OnboardingViewHolder, position: Int) {
        holder.binding.apply {
            tvOnboardingText.text = onboardingList[position].viewPagerTitle
            imgViewPager.setImageResource(onboardingList[position].viewPagerImg)
        }
    }
}