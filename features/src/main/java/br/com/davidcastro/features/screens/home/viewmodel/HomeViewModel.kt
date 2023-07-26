package br.com.davidcastro.features.screens.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.davidcastro.data.usecase.GetCuratedPhotosUseCase
import br.com.davidcastro.features.screens.home.model.BannerState
import br.com.davidcastro.features.screens.home.model.HomeState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getCuratedPhotosUseCase: GetCuratedPhotosUseCase
): ViewModel() {

    private val _homeState = MutableStateFlow(HomeState())
    val homeState: StateFlow<HomeState> = _homeState

    fun getCuratedBannerPhotos(page: Int) = viewModelScope.launch {
        setBannerLoading(true)

        getCuratedPhotosUseCase.getCuratedPhotos(page)
            .onSuccess {
                _homeState.value = HomeState(BannerState(response = it))
            }
            .onFailure {
                _homeState.value = HomeState(BannerState(hasError = true))
            }
    }

    private fun setBannerLoading(isLoading: Boolean) {
        _homeState.value = HomeState(BannerState(isLoading = isLoading))
    }
}