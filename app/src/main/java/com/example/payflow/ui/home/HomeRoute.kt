package com.example.payflow.ui.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun HomeRoute(
    homeViewModel: HomeViewModel = viewModel(),
    modifier: Modifier
){
    val uiState by homeViewModel.uiState.collectAsStateWithLifecycle()

    HomeScreen(
        modifier = modifier ,
        onCashOut = homeViewModel::cashOut,
        onAmountSelected = homeViewModel::selectedAmount,
        uiState = uiState,
    )
}