package br.com.davidcastro.features.screens.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.davidcastro.data.model.PhotoResponse
import br.com.davidcastro.data.usecase.getcuratedphotosusecase.GetCuratedPhotosUseCase
import br.com.davidcastro.data.usecase.getpopularphotosusecase.GetPopularPhotosUseCase
import br.com.davidcastro.features.screens.home.model.state.BannerState
import br.com.davidcastro.features.screens.home.model.state.HomeState
import br.com.davidcastro.features.screens.home.model.state.PopularState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getCuratedPhotosUseCase: GetCuratedPhotosUseCase,
    private val getPopularPhotosUseCase: GetPopularPhotosUseCase
): ViewModel() {

    private val _homeState = MutableStateFlow(HomeState())
    val homeState: StateFlow<HomeState> = _homeState

    fun getCuratedBannerPhotos(page: Int) = viewModelScope.launch {
        setBannerLoading(true)

        getCuratedPhotosUseCase.getCuratedPhotos(page)
            .onSuccess {
                updateBannerState(response = it)
            }
            .onFailure {
                updateBannerState(hasError = true)
            }
    }

    fun getPopularPhotos(page: Int) {
        viewModelScope.launch {

            getPopularPhotosUseCase.getPopularPhotos(page)
                .onSuccess {
                    updatePopularState(response = it)
                }
                .onFailure {
                    updatePopularState(hasError = true)
                }
        }
    }

    private fun setBannerLoading(isLoading: Boolean) {
        _homeState.value = HomeState(BannerState(isLoading = isLoading))
    }

    private fun updateBannerState(
        isLoading: Boolean = false,
        response: PhotoResponse? = null,
        hasError: Boolean = false,
    ) {
       _homeState.value = _homeState.value.copy(bannerState = BannerState(
           isLoading = isLoading,
           response = response,
           hasError = hasError
       ))
    }

    private fun updatePopularState(
        isLoading: Boolean = false,
        response: PhotoResponse? = null,
        hasError: Boolean = false,
    ) {
        _homeState.value = _homeState.value.copy(popularState = PopularState(
            isLoading = isLoading,
            response = response,
            hasError = hasError
        ))
    }
}