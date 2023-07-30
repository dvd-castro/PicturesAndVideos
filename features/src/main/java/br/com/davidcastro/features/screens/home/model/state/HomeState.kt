package br.com.davidcastro.features.screens.home.model.state

import br.com.davidcastro.data.model.PhotoResponse

data class HomeState(
    val bannerState: BannerState = BannerState(),
    val popularState: PopularState = PopularState()
)

data class BannerState(
    val isLoading: Boolean = false,
    val response: PhotoResponse? = null,
    val hasError: Boolean = false
)

data class PopularState(
    val isLoading: Boolean = false,
    val response: PhotoResponse? = null,
    val hasError: Boolean = false
)