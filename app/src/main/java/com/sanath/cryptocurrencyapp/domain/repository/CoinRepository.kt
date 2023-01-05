package com.sanath.cryptocurrencyapp.domain.repository

import com.sanath.cryptocurrencyapp.data.remote.dto.coin.CoinDto
import com.sanath.cryptocurrencyapp.data.remote.dto.coin_detail.CoinDetailDto

interface CoinRepository {

    suspend fun getCoins(): List<CoinDto>

    suspend fun getCoinById(coinId: String): CoinDetailDto

}