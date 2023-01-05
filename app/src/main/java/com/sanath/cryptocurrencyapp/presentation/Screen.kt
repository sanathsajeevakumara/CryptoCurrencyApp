package com.sanath.cryptocurrencyapp.presentation

sealed class Screen(val route: String) {
    object CoinListScreen: Screen("Coin_List_Screen")
    object CoinDetailScreen: Screen("Coin_Detail_Screen")
}