package br.com.davidcastro.features.screens.curated.data

import br.com.davidcastro.data.model.Photo

data class CuratedState(
    val isLoading: Boolean = false,
    val photos: MutableList<Photo> = mutableListOf(),
    val hasError: Boolean = false,
    val hasEnd: Boolean = false,
    val nextPage: Int = 1
)