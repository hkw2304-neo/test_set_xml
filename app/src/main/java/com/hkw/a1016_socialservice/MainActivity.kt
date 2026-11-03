package com.hkw.a1016_socialservice

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.hkw.a1016_socialservice.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    companion object {
        val test: String= "wrwrw"
    }


    private lateinit var _binding: ActivityMainBinding
    private val TAG = javaClass.name


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "oncreate go1")
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(_binding.root)
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "oncreate go2")
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "oncreate g3")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "oncreate go4")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "oncreate go5")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "oncreate go6")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "oncreate go7")
    }


}
