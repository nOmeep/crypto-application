package com.example.cryptocurrencyapplication.domain.use_case.get_coins

import com.example.cryptocurrencyapplication.common.Resource
import com.example.cryptocurrencyapplication.data.remote.dto.toCoin
import com.example.cryptocurrencyapplication.domain.model.Coin
import com.example.cryptocurrencyapplication.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCoinsUseCase @Inject constructor(
    // inject an interface to replace whenever we need it
    private val repository: CoinRepository
) {
    // it's not necessary to override invoke fun but it looks cleaner
    operator fun invoke(): Flow<Resource<List<Coin>>> = flow {
        try {
            emit(Resource.Loading())
            val coins = repository.getCoins()
            emit(Resource.Success(coins.map { it.toCoin() }))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An error something goes wrong"))
        } catch (e: IOException) {
            emit(Resource.Error(e.localizedMessage ?: "Couldn't reach server. Check your internet connection"))
        }
    }
}