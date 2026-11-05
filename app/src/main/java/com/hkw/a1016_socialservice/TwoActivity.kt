package com.hkw.a1016_socialservice

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hkw.a1016_socialservice.databinding.ActivityTwoBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class TwoActivity : AppCompatActivity(){

    private lateinit var _binding: ActivityTwoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val temp = intent.getStringExtra("twoNumber")
        _binding = ActivityTwoBinding.inflate(layoutInflater)

        _binding.twoText.text = temp

        setContentView(_binding.root)
    }


}