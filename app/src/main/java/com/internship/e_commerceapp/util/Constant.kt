package com.internship.e_commerceapp.util

import android.content.Context
import com.internship.e_commerceapp.R
import com.internship.e_commerceapp.data.model.OnboardingItem

object Constant {
    fun viewPagerItemsList(context: Context): List<OnboardingItem> {
        return listOf(
            OnboardingItem(R.drawable.img_onboarding_one,
                context.resources.getString(R.string.we_provide_high_quality_products_just_for_you)),
            OnboardingItem(R.drawable.img_onboarding_two,
                context.resources.getString(R.string.onboarding_viewpager_text_two)),
            OnboardingItem(R.drawable.img_onboarding_three,
                context.resources.getString(R.string.onboarding_viewpager_text_three))
        )
    }
}