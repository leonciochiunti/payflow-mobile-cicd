package com.example.payflow.ui.home

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.payflow.ui.home.AmountButton

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    uiState: HomeUiState,
    onAmountSelected: (Double) -> Unit,
    onCashOut: () -> Unit
){
    var showDetails by remember {
        mutableStateOf(false)
    }

    Column (
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    )
    {
        Text(
           text = "Available balance"
        )

        Text(
            text = "$${uiState.availableBalance}"
        )

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        Text(
            text = "Pay period"
        )

        Text(
            text = "$${uiState.availableBalance} / $${uiState.payPeriodLimit}"
        )
        LinearProgressIndicator(
            progress = { uiState.progress },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer (
            modifier = Modifier.height(16.dp)
        )

        TextButton(
            onClick = { showDetails = !showDetails }
        ) {
            Text(
                text =
                    if (showDetails) {
                        "Hide Details"
                    } else {
                        "Show Details"
                    }
            )
        }


        Row {
            AmountButton (
                amount = 25.0,
                selectedAmount = uiState.selectedAmount,
                onAmountSelected = onAmountSelected
            )

            AmountButton (
                amount = 50.0,
                selectedAmount = uiState.selectedAmount,
                onAmountSelected = onAmountSelected
            )

            AmountButton (
                amount = 100.0,
                selectedAmount = uiState.selectedAmount,
                onAmountSelected = onAmountSelected
            )
        }

        Text(
            text = "Selected: $${uiState.selectedAmount}"
        )
        Button(
            enabled = uiState.canCashOut,
            onClick = onCashOut,
        ) {
            Text("Cash Out $${uiState.selectedAmount}")
        }

        Spacer (
            modifier = Modifier.height(16.dp)
        )

        if (showDetails){
            Column {
                Text(
                    text = "Daily limit: $${uiState.dailyLimit}"
                )
                Text(
                    text = "Pay period limit: $${uiState.payPeriodLimit}"
                )
            }
        }
    }
}

@Composable
fun AmountButton(
    amount: Double,
    selectedAmount: Double,
    onAmountSelected: (Double) -> Unit
){
    val isSelected = amount == selectedAmount
    if (isSelected) {
        Button(
            onClick = {
                onAmountSelected(amount)
            }
        ) {
            Text("$${amount.toInt()}")
        }
    } else {
        OutlinedButton(
            onClick = {
                onAmountSelected(amount)
            }
        ) {
            Text("$${amount.toInt()}")
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen(
        uiState = HomeUiState(),
        onAmountSelected = {},
        onCashOut = {} )
}

