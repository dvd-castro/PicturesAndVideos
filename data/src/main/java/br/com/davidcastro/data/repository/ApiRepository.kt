package br.com.davidcastro.data.repository

import br.com.davidcastro.data.model.PhotoResponse
import retrofit2.Response

interface ApiRepository {
    suspend fun getCuratedPhotos(page: Int): Response<PhotoResponse>
    suspend fun getPopularPhotos(): Response<PhotoResponse>
}