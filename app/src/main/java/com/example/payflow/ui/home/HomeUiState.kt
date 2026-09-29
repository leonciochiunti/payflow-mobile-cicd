package com.example.payflow.ui.home

data class HomeUiState (
    val availableBalance: Double = 425.0,
    val dailyLimit: Double = 150.0,
    val payPeriodLimit: Double = 1000.0,
    val selectedAmount: Double = 50.0
) {

    val canCashOut: Boolean
        get() =
            selectedAmount > 0 &&
                    selectedAmount <= availableBalance &&
                    selectedAmount < dailyLimit

    val progress: Float
        get() = (availableBalance / payPeriodLimit)
            .toFloat()
            .coerceIn(0f,1f)
}