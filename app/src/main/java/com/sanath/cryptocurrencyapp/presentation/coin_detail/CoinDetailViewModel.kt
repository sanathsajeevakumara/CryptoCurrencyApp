package com.sanath.cryptocurrencyapp.presentation.coin_detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sanath.cryptocurrencyapp.common.Constants.PARAM_COIN_ID
import com.sanath.cryptocurrencyapp.common.Resource
import com.sanath.cryptocurrencyapp.domain.use_case.get_coin.GetCoinUseCase
import com.sanath.cryptocurrencyapp.domain.use_case.get_coins.GetCoinsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CoinDetailViewModel @Inject constructor(
    private val getCoinUseCase: GetCoinUseCase,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    var coinDetailState by mutableStateOf(CoinDetailState())
        private set

    init {
        savedStateHandle.get<String>(PARAM_COIN_ID)?.let { coinId ->
            getCoinDetail(coinId)
        }
    }

    private fun getCoinDetail(coinId: String) {
        getCoinUseCase(coinId).onEach { result ->
            coinDetailState = when (result) {
                is Resource.Error -> {
                    CoinDetailState(error = result.message ?: "An Unexpected error occured")
                }
                is Resource.Loading -> {
                    CoinDetailState(isLoading = true)
                }
                is Resource.Success -> {
                    CoinDetailState(
                        coinDetail = result.data
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

}