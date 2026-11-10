package com.hkw.a1016_socialservice.ui.screen.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.hkw.a1016_socialservice.databinding.SplashActivityBinding
import com.hkw.a1016_socialservice.ui.screen.viewmodel.SplashViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SplashActivity: AppCompatActivity() {

    private lateinit var _binding: SplashActivityBinding
    private val _viewModel: SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = SplashActivityBinding.inflate(layoutInflater)

        setContentView(_binding.root)
        _viewModel.splashCountDecrease()
        lifecycleScope.launch {
                repeatOnLifecycle(Lifecycle.State.STARTED){
                    _viewModel.state.collect{
                    _binding.splashCount.text = it.splashCount.toString()
                        if(it.splashCount == 0){
                            // collect 안에서 this느 코루틴을 가리킨다.
                            val intent = Intent(this@SplashActivity, ContainterActivity::class.java)
                            intent.apply {
                                putExtra("key", "스플래시")
                            }
                            startActivity(intent)
                            finish()
                            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
                    }
                }

            }
        }
    }
}