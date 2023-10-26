package br.com.davidcastro.features.screens.photodetails.data

import br.com.davidcastro.data.model.Photo

data class PhotoDetail(
    val selectedIndex: Int = 0,
    val photos: List<Photo> = emptyList(),
)