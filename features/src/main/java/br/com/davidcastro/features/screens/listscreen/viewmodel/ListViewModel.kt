package br.com.davidcastro.features.screens.listscreen.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.davidcastro.data.model.PhotoResponse
import br.com.davidcastro.data.usecase.GetCuratedPhotosUseCase
import br.com.davidcastro.data.usecase.GetPopularPhotosUseCase
import br.com.davidcastro.features.screens.listscreen.state.ListScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val getCuratedPhotosUseCase: GetCuratedPhotosUseCase,
    private val getPopularPhotosUseCase: GetPopularPhotosUseCase
): ViewModel() {

    private val _listScreenState = MutableStateFlow(ListScreenState())
    val listScreenState: StateFlow<ListScreenState> = _listScreenState

    fun getCuratedPhotos(page: Int) = viewModelScope.launch {
        showLoad(true)
        val result = getCuratedPhotosUseCase.getCuratedPhotos(page)

        result.onSuccess { response ->
            response?.let {
                updatePhotoListStateOnSuccess(it)
            }
            showLoad(false)
        }

        result.onFailure {
            showLoad(false)
            _listScreenState.value = listScreenState.value.copy(hasError = true)
        }
    }

    fun getPopularPhotos(page: Int) = viewModelScope.launch {
        showLoad(true)
        val result = getPopularPhotosUseCase.getPopularPhotos(page)

        result.onSuccess { response ->
            response?.let {
                updatePhotoListStateOnSuccess(it)
            }
            showLoad(false)
        }

        result.onFailure {
            showLoad(false)
            _listScreenState.value = listScreenState.value.copy(hasError = true)
        }
    }

    private fun updatePhotoListStateOnSuccess(response: PhotoResponse) {
        if(response.photos.isNotEmpty()) {
            val photos = _listScreenState.value.photos.toMutableList()
            photos.addAll(response.photos)
            _listScreenState.value = listScreenState.value.copy(photos = photos, nextPage = response.page + 1)
        } else {
            _listScreenState.value = listScreenState.value.copy(hasEnd = true)
        }
    }

    private fun showLoad(enable: Boolean) {
        _listScreenState.value = listScreenState.value.copy(hasError = false, isLoading = enable)
    }
}