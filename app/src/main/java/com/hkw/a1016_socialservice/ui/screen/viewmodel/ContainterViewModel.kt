package com.hkw.a1016_socialservice.ui.screen.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.hkw.a1016_socialservice.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

data class ContainterViewState(
    val test: String = "컨테이너 뷰모델",
    val visibleNav: List<Int> = listOf(R.id.mainFragment)
)
@HiltViewModel
class ContainterViewModel  @Inject constructor(): ViewModel() {

    private val _state = MutableStateFlow(ContainterViewState())
    val state = _state.asStateFlow()

    init{
        Log.d("ContainterViewModel", "ContainterViewModel created")
    }

    fun changeText(){
        _state.update{
            it.copy(
                test = "change test"
            )
        }
    }

}