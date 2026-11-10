package com.hkw.a1016_socialservice.ui.screen.viewmodel

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hkw.a1016_socialservice.ui.screen.view.ContainterActivity
import com.hkw.a1016_socialservice.ui.screen.view.SplashActivity
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.time.Duration.Companion.milliseconds

data class SplashState(
    val splashCount: Int = 5
)


@HiltViewModel
class SplashViewModel @Inject constructor(
    @ApplicationContext context: Context
): ViewModel(){

    private val _state = MutableStateFlow(SplashState())
    val state = _state.asStateFlow()
    init {
        Log.d("SplashViewModel", "SplashViewModel create")
    }

    fun splashCountDecrease() {
        viewModelScope.launch {
            while(_state.value.splashCount > 0){
                delay(1000.milliseconds)
                _state.update{
                    it.copy(
                        splashCount = it.splashCount -1
                    )
                }
            }
        }
    }
    fun mainAcTivity(context: SplashActivity) : Intent {
        val intent = Intent(context, ContainterActivity::class.java)

        return intent
    }
}