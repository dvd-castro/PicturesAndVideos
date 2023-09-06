package br.com.davidcastro.data.usecase.getsearchphotosusecase

import br.com.davidcastro.data.model.PhotoResponse

interface GetSearchPhotosUseCase {
    suspend fun getSearchPhotos(query: String, page: Int): Result<PhotoResponse?>
}