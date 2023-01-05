package com.sanath.cryptocurrencyapp.data.repository

import com.sanath.cryptocurrencyapp.data.remote.CoinPaprikaApi
import com.sanath.cryptocurrencyapp.data.remote.dto.coin.CoinDto
import com.sanath.cryptocurrencyapp.data.remote.dto.coin_detail.CoinDetailDto
import com.sanath.cryptocurrencyapp.domain.repository.CoinRepository
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val api: CoinPaprikaApi
) : CoinRepository {

    override suspend fun getCoins(): List<CoinDto> {
        return api.getCoins()
    }

    override suspend fun getCoinById(coinId: String): CoinDetailDto {
        return api.getCoinById(coinId)
    }
}