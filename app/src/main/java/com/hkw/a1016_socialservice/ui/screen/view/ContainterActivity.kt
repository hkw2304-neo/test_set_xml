package com.hkw.a1016_socialservice.ui.screen.view

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.hkw.a1016_socialservice.R
import com.hkw.a1016_socialservice.databinding.ContainerActivityBinding
import com.hkw.a1016_socialservice.ui.screen.viewmodel.ContainterViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ContainterActivity : AppCompatActivity(){

    private lateinit var _binding: ContainerActivityBinding
    private val _viewModel: ContainterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ContainerActivityBinding.inflate(layoutInflater)
        setContentView(_binding.root)

        val test:String? = intent.getStringExtra("key")
        if(test != null){
            Log.d("ContainterActivity", test)
        }
        _viewModel.changeText()


        val navHost = supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        val navController = navHost.navController
        navController.apply {
            addOnDestinationChangedListener {_, destination, _ ->
                _binding.navBottom.isVisible = destination.id in _viewModel.state.value.visibleNav
            }
        }
        _binding.navBottom.setupWithNavController(navController)
    }
}