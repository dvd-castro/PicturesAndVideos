package br.com.davidcastro.features.screens.home.model.state

import br.com.davidcastro.data.model.PhotoResponse

data class HomeState(
    val hasLoading: Boolean = false,
    val hasError: Boolean = false,
    val bannerResponse: PhotoResponse? = null,
    val popularResponse: PhotoResponse? = null,
)