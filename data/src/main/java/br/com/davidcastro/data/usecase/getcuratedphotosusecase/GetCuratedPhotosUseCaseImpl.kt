package br.com.davidcastro.data.usecase.getcuratedphotosusecase

import br.com.davidcastro.data.model.CustomException
import br.com.davidcastro.data.model.PhotoResponse
import br.com.davidcastro.data.repository.ApiRepository
import javax.inject.Inject

class GetCuratedPhotosUseCaseImpl @Inject constructor(private val api: ApiRepository): GetCuratedPhotosUseCase {
    override suspend fun getCuratedPhotos(page: Int): Result<PhotoResponse?> {
        return try {
            val result = api.getCuratedPhotos(page)

            if(result.isSuccessful) {
                Result.success(result.body())
            } else {
                Result.failure(CustomException(result.message()))
            }
        } catch (ex: Exception) {
            Result.failure(ex)
        }
    }
}