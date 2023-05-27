package br.com.davidcastro.data.usecase

import br.com.davidcastro.data.model.PhotoResponse
import br.com.davidcastro.data.repository.ApiRepository

class GetCuratedPhotosUseCaseImpl(private val api: ApiRepository): GetCuratedPhotosUseCase {

    override suspend fun getCuratedPhotos(): PhotoResponse? {
        val result = api.getCuratedPhotos()
        return if(result.isSuccessful) {
            result.body()
        } else {
            null
        }
    }
}