package com.hkw.a1016_socialservice

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.hkw.a1016_socialservice.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
//        installSplashScreen()          // super.onCreate() 이전에 호출
        super.onCreate(savedInstanceState)
        Log.d("MainActivity", "MainActivity")

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // NavHostFragment가 XML에서 자동으로 붙기 때문에 별도 코드 불필요
    }
}
