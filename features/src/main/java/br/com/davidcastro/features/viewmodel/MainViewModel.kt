package br.com.davidcastro.features.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.davidcastro.data.usecase.GetCuratedPhotosUseCase
import br.com.davidcastro.features.screens.curated.data.CuratedState
import br.com.davidcastro.features.screens.home.data.BannerState
import br.com.davidcastro.features.screens.home.data.HomeState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getCuratedPhotosUseCase: GetCuratedPhotosUseCase
): ViewModel() {

    private val _homeState = MutableStateFlow(HomeState())
    val homeState: StateFlow<HomeState> = _homeState

    private val _curatedState = MutableStateFlow(CuratedState())
    val curatedState: StateFlow<CuratedState> = _curatedState

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

    fun getCuratedPhotos(page: Int) = viewModelScope.launch {
        val result = getCuratedPhotosUseCase.getCuratedPhotos(page)

        result.onSuccess { response ->
            val newPhotos = curatedState.value.photos.toMutableList()
            response?.photos?.let { photos ->
                newPhotos.addAll(photos)
            }

            _curatedState.value = curatedState.value.copy(photos = newPhotos)
        }

        result.onFailure {
            _curatedState.value = curatedState.value.copy(hasError = true)
        }
    }

    private fun setBannerLoading(isLoading: Boolean) {
        _homeState.value = HomeState(BannerState(isLoading = isLoading))
    }
}