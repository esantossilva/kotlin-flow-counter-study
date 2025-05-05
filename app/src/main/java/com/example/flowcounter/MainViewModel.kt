package com.example.flowcounter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {

    val countDownFlow = flow<Int> {
        val startingValue = 10
        var currentValue = startingValue

        emit(startingValue)

        while (currentValue > 0) {
            delay(1000L)
            currentValue--
            emit(currentValue)
        }
    }

    init {
        collectCounterFlow()
    }

    private fun collectCounterFlow() {
        viewModelScope.launch {

            /*
            // Will emit every output
            countDownFlow.collect { time ->
                delay(1500L)
                println("The current time is $time")
            }
            */

            // Will cancel previous block when new emission comes in and will only emit the latest.
            // In this case, will only emit the 0.
            countDownFlow.collectLatest { time ->
                delay(1500L)
                println("The current time is $time")
            }
        }
    }
}
