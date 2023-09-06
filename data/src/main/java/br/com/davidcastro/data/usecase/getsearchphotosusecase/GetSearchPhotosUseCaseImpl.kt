package br.com.davidcastro.data.usecase.getsearchphotosusecase

import br.com.davidcastro.data.model.CustomException
import br.com.davidcastro.data.model.PhotoResponse
import br.com.davidcastro.data.repository.ApiRepository
import javax.inject.Inject

class GetSearchPhotosUseCaseImpl @Inject constructor(private val api: ApiRepository): GetSearchPhotosUseCase {
    override suspend fun getSearchPhotos(query: String, page: Int): Result<PhotoResponse?> {
        return try {
            val result = api.getSearchPhotos(query, page)

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