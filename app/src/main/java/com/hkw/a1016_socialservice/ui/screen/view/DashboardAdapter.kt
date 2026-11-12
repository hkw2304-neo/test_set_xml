package com.hkw.a1016_socialservice.ui.screen.view

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class DashboardTabAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount() = 2
    override fun createFragment(position: Int): Fragment = when (position) {
        0 -> DashboardTab1Fragment()
        1 -> DashboardTab2Fragment()
        else -> DashboardTab1Fragment()
    }
}