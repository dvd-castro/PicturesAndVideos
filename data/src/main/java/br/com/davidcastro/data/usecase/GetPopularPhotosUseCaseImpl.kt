package br.com.davidcastro.data.usecase

import br.com.davidcastro.data.model.CustomException
import br.com.davidcastro.data.model.PhotoResponse
import br.com.davidcastro.data.repository.ApiRepository
import javax.inject.Inject

class GetPopularPhotosUseCaseImpl @Inject constructor(private val api: ApiRepository): GetPopularPhotosUseCase {
    override suspend fun getPopularPhotos(page: Int): Result<PhotoResponse?> {
        return try {
            val result = api.getPopularPhotos(page)

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