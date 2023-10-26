package br.com.davidcastro.data.usecase.downloadimageusecase

import android.graphics.Bitmap

interface GetImageBitmapUseCase {
    suspend operator fun invoke(url: String): Bitmap
}