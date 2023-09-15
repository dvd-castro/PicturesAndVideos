package br.com.davidcastro.features.screens.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.davidcastro.data.model.PhotoResponse
import br.com.davidcastro.data.usecase.getcuratedphotosusecase.GetCuratedPhotosUseCase
import br.com.davidcastro.data.usecase.getpopularphotosusecase.GetPopularPhotosUseCase
import br.com.davidcastro.features.screens.home.model.state.HomeState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getCuratedPhotosUseCase: GetCuratedPhotosUseCase,
    private val getPopularPhotosUseCase: GetPopularPhotosUseCase,
): ViewModel() {

    private val _homeState = MutableStateFlow(HomeState())
    val homeState: StateFlow<HomeState> = _homeState

    fun getCuratedBannerPhotos(page: Int) = viewModelScope.launch {
        updateHomeState(hasLoading = true)
        getCuratedPhotosUseCase.getCuratedPhotos(page)
            .onSuccess {
                updateHomeState(bannerResponse = it)
                getPopularPhotos(page)
            }
            .onFailure {
                updateHomeState(hasError = true)
            }
    }

    fun getPopularPhotos(page: Int) {
        viewModelScope.launch {
            getPopularPhotosUseCase.getPopularPhotos(page)
                .onSuccess {
                    updateHomeState(popularResponse = it)
                }
                .onFailure {
                    updateHomeState(hasError = true)
                }

            updateHomeState(hasLoading = false)
        }
    }

    private fun updateHomeState(
        hasLoading: Boolean = _homeState.value.hasLoading,
        hasError: Boolean = _homeState.value.hasError,
        bannerResponse: PhotoResponse? = _homeState.value.bannerResponse,
        popularResponse: PhotoResponse? = _homeState.value.popularResponse,
    ) {
       _homeState.value = HomeState(
           hasLoading = hasLoading,
           hasError = hasError,
           bannerResponse = bannerResponse,
           popularResponse = popularResponse,
       )
    }
}