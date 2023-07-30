package br.com.davidcastro.features.screens.popular.state

import br.com.davidcastro.data.model.Photo

data class PopularState(
    val isLoading: Boolean = false,
    val photos: MutableList<Photo> = mutableListOf(),
    val hasError: Boolean = false,
    val hasEnd: Boolean = false,
    val nextPage: Int = 1
)