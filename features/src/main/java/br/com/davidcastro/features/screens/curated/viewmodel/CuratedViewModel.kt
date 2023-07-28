package br.com.davidcastro.features.screens.curated.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.davidcastro.data.usecase.GetCuratedPhotosUseCase
import br.com.davidcastro.features.screens.curated.model.state.CuratedState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CuratedViewModel @Inject constructor(
    private val getCuratedPhotosUseCase: GetCuratedPhotosUseCase
): ViewModel() {

    private val _curatedState = MutableStateFlow(CuratedState())
    val curatedState: StateFlow<CuratedState> = _curatedState

    fun getCuratedPhotos(page: Int) = viewModelScope.launch {
        showLoad(true)
        val result = getCuratedPhotosUseCase.getCuratedPhotos(page)

        result.onSuccess { response ->
            response?.let {
                if(it.photos.isNotEmpty()) {
                    val photos = curatedState.value.photos.toMutableList()
                    photos.addAll(it.photos)
                    _curatedState.value = curatedState.value.copy(photos = photos, nextPage = it.page + 1)
                } else {
                    _curatedState.value = curatedState.value.copy(hasEnd = true)
                }
            }
            showLoad(false)
        }

        result.onFailure {
            showLoad(false)
            _curatedState.value = curatedState.value.copy(hasError = true)
        }
    }

    private fun showLoad(enable: Boolean) {
        _curatedState.value = curatedState.value.copy(hasError = false, isLoading = enable)
    }
}