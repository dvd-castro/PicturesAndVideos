package br.com.davidcastro.data.usecase.getcuratedphotosusecase

import br.com.davidcastro.data.model.PhotoResponse

interface GetCuratedPhotosUseCase {
    suspend fun getCuratedPhotos(page: Int): Result<PhotoResponse?>
}