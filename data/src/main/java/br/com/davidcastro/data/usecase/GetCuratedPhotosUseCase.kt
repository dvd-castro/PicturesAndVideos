package br.com.davidcastro.data.usecase

import br.com.davidcastro.data.model.PhotoResponse

interface GetCuratedPhotosUseCase {
    suspend fun getCuratedPhotos(): PhotoResponse?
}