package br.com.davidcastro.data.repository

import br.com.davidcastro.data.api.Api
import br.com.davidcastro.data.model.PhotoResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class ApiRepositoryImpl @Inject constructor(private val api: Api): ApiRepository {

    override suspend fun getCuratedPhotos(page: Int): Response<PhotoResponse> = withContext(Dispatchers.IO) {
        return@withContext api.getCuratedPhotos(page)
    }

    override suspend fun getPopularPhotos(): Response<PhotoResponse> = withContext(Dispatchers.IO) {
        return@withContext api.getPopularPhotos()
    }
}