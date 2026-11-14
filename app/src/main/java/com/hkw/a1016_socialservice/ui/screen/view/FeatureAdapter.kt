package com.hkw.a1016_socialservice.ui.screen.view

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class FeatureAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount() = 2
    override fun createFragment(position: Int): Fragment = when (position) {
        0 -> FeatureCameraFragment()
        1 -> FeatureImageFragment()
        else -> FeatureCameraFragment()
    }
}