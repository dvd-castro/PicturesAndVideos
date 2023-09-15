package br.com.davidcastro.data.usecase.getsearchphotosusecase

import br.com.davidcastro.data.model.PhotoResponse

interface GetSearchPhotosUseCase {
    suspend operator fun invoke(query: String, page: Int): Result<PhotoResponse?>
}