package com.example.cryptocurrencyapplication.data.repository

import com.example.cryptocurrencyapplication.data.remote.CoinPaprikaApi
import com.example.cryptocurrencyapplication.domain.repository.CoinRepository
import javax.inject.Inject

class CoinRepositoryImplementation @Inject constructor(
    private val api: CoinPaprikaApi
) : CoinRepository {
    override suspend fun getCoins() =
        api.getAllCoins()

    override suspend fun getCoinById(coinId: String) =
        api.getSingleCoin(coinId)
}