package com.example.payflow.ui.home

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class HomeViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    fun cashOut() {
        _uiState.update { currentState ->
            if(!currentState.canCashOut){
                return@update currentState
            }

            val newBalance =
                currentState.availableBalance -
                        currentState.selectedAmount

                currentState.copy(
                    availableBalance = newBalance,
                )
        }
    }

    fun selectedAmount(amount: Double) {
        _uiState.update { currentState ->
                currentState.copy(
                    selectedAmount = amount
                )
        }
    }

}