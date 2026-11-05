package com.hkw.a1016_socialservice

import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject


data class OneState(
    val test: String = "sample"
)

@HiltViewModel
class OneViewModel @Inject constructor() : ViewModel() {

    private val _state = MutableStateFlow(OneState())
    val state = _state.asStateFlow()


    init{
        _state.update{
            it.copy(
                test = "change test"
            )
        }
    }
    fun btnClick(context: Context): Intent{
        val intent = Intent(context, TwoActivity::class.java)
        intent.apply {
            putExtra("twoNumber","twNumber!!")
        }
        return intent
    }


}