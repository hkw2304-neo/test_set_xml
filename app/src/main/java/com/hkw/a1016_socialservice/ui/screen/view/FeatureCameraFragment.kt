package com.hkw.a1016_socialservice.ui.screen.view

import android.Manifest
import android.content.pm.PackageManager
import android.media.MediaRecorder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.hkw.a1016_socialservice.databinding.DashboardTextFragmentBinding
import com.hkw.a1016_socialservice.databinding.FeatureCameraFragmentBinding
import com.hkw.a1016_socialservice.ui.screen.viewmodel.ContainterViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import retrofit2.http.Url
import java.io.File

@AndroidEntryPoint
class FeatureCameraFragment : Fragment() {

    private var _binding: FeatureCameraFragmentBinding? = null
    private val binding get() = _binding!!

    private val _viewModel: ContainterViewModel by activityViewModels()
    private var _photoUri: Uri? = null

    private var recorder: MediaRecorder? = null
    private var audioFile: File? = null
    private var isRecording = false


    private val _takePicture =
        registerForActivityResult(ActivityResultContracts.TakePicture()) { it ->
            if (it) {
                Log.d("카메라", "촬영 성공: $_photoUri")
            } else {
                Log.d("카메라", "촬영 성공 실패")
            }
        }
    private val _cameraPermission =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { it ->
            if (it) {

                launchCamera()
            } else {
                Toast.makeText(requireContext(), "카메라 권한 필요", Toast.LENGTH_SHORT).show()
            }
        }
    private val _recordPermission =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { it ->
            if (it) {
                startRecording()
            } else {
                Toast.makeText(requireContext(), "마이크 권한 필요", Toast.LENGTH_SHORT).show()
            }
        }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FeatureCameraFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.fetureCameraButton.setOnClickListener {
            Log.d("카메라 버튼", "카메라 버튼 클릭중")
            if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_GRANTED
            ) {
                launchCamera()
            } else {
                _cameraPermission.launch(Manifest.permission.CAMERA)
            }
        }
        binding.recordBtn.setOnClickListener {
            if (ContextCompat.checkSelfPermission(
                    requireContext(),
                    Manifest.permission.RECORD_AUDIO
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                if (isRecording) {
                    stopRecording()
                } else {
                    startRecording()
                }
            } else {
                _recordPermission.launch(Manifest.permission.RECORD_AUDIO)
            }
        }

    }

    private fun launchCamera() {

        val dir = File(requireContext().cacheDir, "photos").apply { mkdirs() }
        val file = File.createTempFile("IMG_", ".jpg", dir)
        val uri = FileProvider.getUriForFile(
            requireContext(), "${requireContext().packageName}.fileprovider", file
        )
        _viewModel.setPhotoUri(uri)

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                _viewModel.state.collect {
                    _photoUri = it.photoUri
                }
            }
        }
        _takePicture.launch(_photoUri)
    }

    private fun startRecording() {
        val dir = File(requireContext().filesDir, "recordings").apply { mkdirs() }
        val file = File(dir, "REC_${System.currentTimeMillis()}.m4a")
        audioFile = file

        recorder = (if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S)
            MediaRecorder(requireContext())
        else
            @Suppress("DEPRECATION") MediaRecorder()
                ).apply {
                setAudioSource(MediaRecorder.AudioSource.MIC)
                setOutputFormat(MediaRecorder.OutputFormat.MPEG_4)
                setAudioEncoder(MediaRecorder.AudioEncoder.AAC)
                setAudioSamplingRate(44100)
                setAudioEncodingBitRate(128_000)
                setOutputFile(file.absolutePath)
                prepare()
                start()
            }
        isRecording = true
        Log.d("녹음", "녹음 시작: ${file.absolutePath}")
        binding.recordBtn.text = "녹음중지"
    }

    private fun stopRecording() {
        try {
            recorder?.stop()
        } catch (e: RuntimeException) {
            audioFile?.delete()
        } finally {
            recorder?.release()
            recorder = null
            isRecording = false
            binding.recordBtn.text = "녹음"
        }
        Log.d("녹음", "녹음 종료: ${audioFile?.absolutePath}")
    }


    override fun onDestroyView() {
        super.onDestroyView()
        if (isRecording) stopRecording()
        _binding = null
    }


}