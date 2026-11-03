package com.hkw.a1016_socialservice

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.hkw.a1016_socialservice.databinding.FragmentHomeBinding
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    fun bindingGET() = _binding!!

//    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d("HomeFragment", "HomeFragment : ${MainActivity.test}")

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // 구독(감지) 상태가 해당 뷰가 파괴 되었을때는 감지하지 말라고 viewLifecycleOwner 안에서 돌게함
        // lifecycleScope만하면 구독 상태가 프래그먼트가 파괴되기 전까지 계속 동작
        // 생각을 해봐야 하는데 uiState가 변경이 안되면 감지를 안해서 lifecycleScope 만써도 무방
        // 구조를 뷰-뷰모델 일대일 매핑 등 방법이 있지만
        // 정석은 viewLifecycleOwner.lifecycleScope 이 형태로 하는 것이 좋을 듯
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
//                viewModel.uiState.collect {
//                    binding.textHello.text = it.userName
//                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}