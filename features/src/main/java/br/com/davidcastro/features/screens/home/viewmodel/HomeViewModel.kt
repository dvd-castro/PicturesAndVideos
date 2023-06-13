package br.com.davidcastro.features.screens.home.viewmodel

import androidx.lifecycle.ViewModel
import br.com.davidcastro.data.usecase.GetCuratedPhotosUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getCuratedPhotosUseCase: GetCuratedPhotosUseCase
): ViewModel() {

}