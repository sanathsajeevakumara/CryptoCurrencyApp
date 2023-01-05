package com.sanath.cryptocurrencyapp.domain.use_case.get_coin

import com.sanath.cryptocurrencyapp.common.Resource
import com.sanath.cryptocurrencyapp.data.remote.dto.coin.toCoin
import com.sanath.cryptocurrencyapp.data.remote.dto.coin_detail.toCoinDetail
import com.sanath.cryptocurrencyapp.domain.model.CoinDetail
import com.sanath.cryptocurrencyapp.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCoinUseCase @Inject constructor(
    private val repository: CoinRepository
) {
    operator fun invoke(coinId: String): Flow<Resource<CoinDetail>> = flow {
        try {
            emit(Resource.Loading())
            val coinById = repository.getCoinById(coinId).toCoinDetail()
            emit(Resource.Success(coinById))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An Unexpected error occured"))
        } catch (e: IOException) {
            emit((Resource.Error("Couldn't reach server. Check you internet connection")))
        }
    }
}