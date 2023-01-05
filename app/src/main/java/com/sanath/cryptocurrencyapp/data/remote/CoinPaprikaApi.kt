package com.sanath.cryptocurrencyapp.data.remote

import com.sanath.cryptocurrencyapp.data.remote.dto.coin.CoinDto
import com.sanath.cryptocurrencyapp.data.remote.dto.coin_detail.CoinDetailDto
import retrofit2.http.GET
import retrofit2.http.Path

interface CoinPaprikaApi {
    @GET("v1/coins")
    suspend fun getCoins(): List<CoinDto>

    @GET("v1/coins/{coinId}")
    suspend fun getCoinById(@Path("coinId") coinId: String): CoinDetailDto
}