package br.com.davidcastro.data.usecase.getpopularphotosusecase

import br.com.davidcastro.data.model.PhotoResponse

interface GetPopularPhotosUseCase {
    suspend fun getPopularPhotos(page: Int): Result<PhotoResponse?>
}