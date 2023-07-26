package br.com.davidcastro.features.screens.photodetails.data

import br.com.davidcastro.data.model.Photo

data class PhotoDetailState(
    val selectedIndex: Int,
    val photos: List<Photo>,
)