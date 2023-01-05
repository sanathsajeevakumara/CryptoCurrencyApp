package com.sanath.cryptocurrencyapp.domain.use_case.get_coins

import com.sanath.cryptocurrencyapp.common.Resource
import com.sanath.cryptocurrencyapp.data.remote.dto.coin.toCoin
import com.sanath.cryptocurrencyapp.domain.model.Coin
import com.sanath.cryptocurrencyapp.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCoinsUseCase @Inject constructor(
    private val repository: CoinRepository
) {
    operator fun invoke(): Flow<Resource<List<Coin>>> = flow {
        try {
            emit(Resource.Loading())
            val coins = repository.getCoins().map { it.toCoin() }
            emit(Resource.Success(coins))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An Unexpected error occured"))
        } catch (e: IOException) {
            emit((Resource.Error("Couldn't reach server. Check you internet connection")))
        }
    }
}