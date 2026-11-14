package com.hkw.a1016_socialservice.ui.screen.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.hkw.a1016_socialservice.databinding.FeatureCameraFragmentBinding
import com.hkw.a1016_socialservice.databinding.FeatureImageFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FeatureImageFragment : Fragment() {

    private var _binding: FeatureImageFragmentBinding? = null
    private val binding get() = _binding!!

    private val _galleryOpen = registerForActivityResult(ActivityResultContracts.PickVisualMedia()){
        it ->
        if(it != null){
            Log.d("갤러리", "갤러리 : $it")
        }else{
            Log.d("갤러리", "갤러리 오류")
    }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FeatureImageFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.featureImageButton.setOnClickListener {
            _galleryOpen.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
        }
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}