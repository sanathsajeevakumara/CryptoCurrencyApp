package com.sanath.cryptocurrencyapp.presentation.coin_list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sanath.cryptocurrencyapp.common.Resource
import com.sanath.cryptocurrencyapp.domain.use_case.get_coins.GetCoinsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CoinListViewModel @Inject constructor(
    private val getCoinsUseCase: GetCoinsUseCase
) : ViewModel() {

    var coinListState by mutableStateOf(CoinListState())
        private set

    init {
        getCoins()
    }

    private fun getCoins() {
        getCoinsUseCase().onEach { result ->
            coinListState = when (result) {
                is Resource.Error -> {
                    CoinListState(error = result.message ?: "An Unexpected error occured")
                }
                is Resource.Loading -> {
                    CoinListState(isLoading = true)
                }
                is Resource.Success -> {
                    CoinListState(
                        coins = result.data ?: emptyList()
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

}