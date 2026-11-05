package com.hkw.a1016_socialservice

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.hkw.a1016_socialservice.databinding.ActivityOneBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class OneActivity : AppCompatActivity(){

    private lateinit var _binding: ActivityOneBinding
    private val viewModel: OneViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityOneBinding.inflate(layoutInflater)

        lifecycleScope.launch {
            viewModel.state.collect{
                _binding.oneText.text = it.test
            }
        }
        _binding.oneBtn.setOnClickListener {
            val intent = viewModel.btnClick(this)
            startActivity(intent)
            finish()
        }
        setContentView(_binding.root)

    }
}