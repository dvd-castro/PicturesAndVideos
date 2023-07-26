package br.com.davidcastro.features.screens.curated.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.davidcastro.data.usecase.GetCuratedPhotosUseCase
import br.com.davidcastro.features.screens.curated.data.CuratedState
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
        val result = getCuratedPhotosUseCase.getCuratedPhotos(page)

        result.onSuccess { response ->
            response?.let {
                val newPhotos = curatedState.value.photos.toMutableList()
                newPhotos.addAll(it.photos)
                _curatedState.value = curatedState.value.copy(photos = newPhotos, nextPage = it.page + 1)
            }
        }

        result.onFailure {
            _curatedState.value = curatedState.value.copy(hasError = true)
        }
    }
}