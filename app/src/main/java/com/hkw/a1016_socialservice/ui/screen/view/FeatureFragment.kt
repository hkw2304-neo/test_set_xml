package com.hkw.a1016_socialservice.ui.screen.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.hkw.a1016_socialservice.databinding.DashboardFragmentBinding
import com.hkw.a1016_socialservice.databinding.FeatureFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FeatureFragment: Fragment(){

    private var _binding: FeatureFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FeatureFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val titles = listOf("카메라", "갤러리")
        binding.viewPager.adapter = FeatureAdapter(this)
        TabLayoutMediator(binding.featuretTabLayout, binding.viewPager) { tab, pos ->
            tab.text = titles[pos]
        }.attach()
    }

    override fun onDestroyView() {
        binding.viewPager.adapter = null
        _binding = null
        super.onDestroyView()
    }




}