package com.example.cryptocurrencyapplication.presentation.coin_detail

import com.example.cryptocurrencyapplication.domain.model.CoinDetail

data class CoinDetailState(
    val isLoading: Boolean = false,
    val coinDetail: CoinDetail? = null,
    val error: String = ""
)