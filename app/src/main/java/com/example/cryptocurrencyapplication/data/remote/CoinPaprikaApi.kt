package com.example.cryptocurrencyapplication.data.remote

import com.example.cryptocurrencyapplication.data.remote.dto.CoinDetailDto
import com.example.cryptocurrencyapplication.data.remote.dto.CoinDto
import retrofit2.http.GET
import retrofit2.http.Path

interface CoinPaprikaApi {
    companion object {
        const val BASE_URL = "https://api.coinpaprika.com"
    }

    @GET("/v1/coins")
    fun getAllCoins(): List<CoinDto>

    @GET("/v1/coins/{coin_id}")
    fun getSingleCoin(
        @Path("coin_id") coinId: String
    ): CoinDetailDto
}