package com.hkw.a1016_socialservice.ui.screen.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.hkw.a1016_socialservice.databinding.MainFragmentBinding
import com.hkw.a1016_socialservice.ui.screen.viewmodel.ContainterViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainFragment : Fragment(){

    private var _binding: MainFragmentBinding? = null
    val binding get() = _binding!!
    private val viewModel: ContainterViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = MainFragmentBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d("MainFragment", viewModel.state.value.test)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



}