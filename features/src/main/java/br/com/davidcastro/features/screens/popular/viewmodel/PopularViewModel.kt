package br.com.davidcastro.features.screens.popular.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.davidcastro.data.usecase.GetPopularPhotosUseCase
import br.com.davidcastro.features.screens.popular.state.PopularState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PopularViewModel @Inject constructor(
    private val getPopularPhotosUseCase: GetPopularPhotosUseCase
): ViewModel() {

    private val _popularState = MutableStateFlow(PopularState())
    val popularState: StateFlow<PopularState> = _popularState

    fun getPopularPhotos(page: Int) = viewModelScope.launch {
        showLoad(true)
        val result = getPopularPhotosUseCase.getPopularPhotos(page)

        result.onSuccess { response ->
            response?.let {
                if(it.photos.isNotEmpty()) {
                    val photos = _popularState.value.photos.toMutableList()
                    photos.addAll(it.photos)
                    _popularState.value = _popularState.value.copy(photos = photos, nextPage = it.page + 1)
                } else {
                    _popularState.value = _popularState.value.copy(hasEnd = true)
                }
            }
            showLoad(false)
        }

        result.onFailure {
            showLoad(false)
            _popularState.value = _popularState.value.copy(hasError = true)
        }
    }

    private fun showLoad(enable: Boolean) {
        _popularState.value = _popularState.value.copy(hasError = false, isLoading = enable)
    }
}