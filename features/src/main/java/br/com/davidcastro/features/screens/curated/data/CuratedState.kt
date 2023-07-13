package br.com.davidcastro.features.screens.curated.data

import br.com.davidcastro.data.model.Photo

data class CuratedState(
    var isLoading: Boolean = false,
    var photos: MutableList<Photo> = mutableListOf(),
    var hasError: Boolean = false
)