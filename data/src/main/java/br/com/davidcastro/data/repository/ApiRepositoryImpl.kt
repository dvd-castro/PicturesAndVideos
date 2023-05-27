package br.com.davidcastro.data.repository

import br.com.davidcastro.data.api.Api
import br.com.davidcastro.data.model.PhotoResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class ApiRepositoryImpl(private val api: Api): ApiRepository {

    override suspend fun getCuratedPhotos(): Response<PhotoResponse> = withContext(Dispatchers.IO) {
        return@withContext api.getCuratedPhotos()
    }

    override suspend fun getPopularPhotos(): Response<PhotoResponse> = withContext(Dispatchers.IO) {
        return@withContext api.getPopularPhotos()
    }
}