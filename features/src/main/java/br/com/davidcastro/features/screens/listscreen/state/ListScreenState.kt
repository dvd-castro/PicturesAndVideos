package br.com.davidcastro.features.screens.listscreen.state

import br.com.davidcastro.data.model.Photo

data class ListScreenState(
    val isLoading: Boolean = false,
    val photos: MutableList<Photo> = mutableListOf(),
    val resultNotFound: Boolean = false,
    val hasError: Boolean = false,
    val hasEnd: Boolean = false,
    val nextPage: Int = 1
)