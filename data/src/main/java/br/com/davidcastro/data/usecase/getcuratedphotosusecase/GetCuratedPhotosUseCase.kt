package br.com.davidcastro.data.usecase.getcuratedphotosusecase

import br.com.davidcastro.data.model.PhotoResponse

interface GetCuratedPhotosUseCase {
    suspend operator fun invoke(page: Int): Result<PhotoResponse?>
}