package br.com.davidcastro.data.usecase.getpopularphotosusecase

import br.com.davidcastro.data.model.PhotoResponse

interface GetPopularPhotosUseCase {
    suspend operator fun invoke(page: Int): Result<PhotoResponse?>
}