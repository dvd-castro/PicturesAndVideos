package br.com.davidcastro.features.screens.home.model.state

import br.com.davidcastro.data.model.PhotoResponse

data class HomeState(
    var bannerState: BannerState = BannerState(),
)

data class BannerState(
    var isLoading: Boolean = false,
    var response: PhotoResponse? = null,
    var hasError: Boolean = false
)