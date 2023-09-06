package br.com.davidcastro.data.repository

import br.com.davidcastro.data.api.Api
import br.com.davidcastro.data.model.PhotoResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class ApiRepositoryImpl @Inject constructor(private val api: Api): ApiRepository {
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO

    override suspend fun getCuratedPhotos(page: Int): Response<PhotoResponse> = withContext(dispatcher) {
        return@withContext api.getCuratedPhotos(page)
    }

    override suspend fun getPopularPhotos(page: Int): Response<PhotoResponse> = withContext(dispatcher) {
        return@withContext api.getPopularPhotos(page = page)
    }

    override suspend fun getSearchPhotos(query: String, page: Int): Response<PhotoResponse> = withContext(dispatcher) {
        return@withContext api.getSearchPhotos(query, page)
    }
}