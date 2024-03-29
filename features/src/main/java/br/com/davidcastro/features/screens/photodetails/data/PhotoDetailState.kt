package br.com.davidcastro.features.screens.photodetails.data

import android.graphics.Bitmap
import br.com.davidcastro.data.model.Photo

data class PhotoDetailState(
    val selectedIndex: Int = 0,
    val photos: List<Photo> = emptyList(),
    val selectedPhotoBitmap: Bitmap? = null
)