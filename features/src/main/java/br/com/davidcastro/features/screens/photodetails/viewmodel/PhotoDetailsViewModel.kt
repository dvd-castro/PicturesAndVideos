package br.com.davidcastro.features.screens.photodetails.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.davidcastro.data.usecase.downloadimageusecase.GetImageBitmapUseCase
import br.com.davidcastro.features.screens.photodetails.data.PhotoDetail
import br.com.davidcastro.features.screens.photodetails.data.PhotoDetailState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PhotoDetailsViewModel @Inject constructor(
    private val getImageBitmapUseCase: GetImageBitmapUseCase
): ViewModel() {

    private val _photoDetailsState = MutableStateFlow(PhotoDetailState())
    val photoDetailsState: StateFlow<PhotoDetailState> = _photoDetailsState

    fun initPhotoDetailsState(photoDetail: PhotoDetail) {
        _photoDetailsState.value = PhotoDetailState(
            selectedIndex = photoDetail.selectedIndex,
            photos = photoDetail.photos
        )
    }

    fun getImageBitmap(url: String) = viewModelScope.launch {
        _photoDetailsState.value = _photoDetailsState.value.copy(selectedPhotoBitmap = getImageBitmapUseCase(url))
    }
}