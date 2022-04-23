package com.example.cryptocurrencyapplication.domain.use_case.get_coin

import com.example.cryptocurrencyapplication.common.Resource
import com.example.cryptocurrencyapplication.data.remote.dto.toCoinDetail
import com.example.cryptocurrencyapplication.domain.model.CoinDetail
import com.example.cryptocurrencyapplication.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCoinUseCase @Inject constructor(
    // inject an interface to replace whenever we need it
    private val repository: CoinRepository
) {
    // it's not necessary to override invoke fun but it looks cleaner
    operator fun invoke(coinId: String): Flow<Resource<CoinDetail>> = flow {
        try {
            emit(Resource.Loading())
            val coinDetailDto = repository.getCoinById(coinId)
            emit(Resource.Success(coinDetailDto.toCoinDetail()))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An error something goes wrong"))
        } catch (e: IOException) {
            emit(Resource.Error(e.localizedMessage ?: "Couldn't reach server. Check your internet connection"))
        }
    }
}