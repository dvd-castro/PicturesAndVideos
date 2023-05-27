package br.com.davidcastro.data.repository

import br.com.davidcastro.data.model.PhotoResponse
import retrofit2.Response

interface ApiRepository {
    suspend fun getCuratedPhotos(): Response<PhotoResponse>
    suspend fun getPopularPhotos(): Response<PhotoResponse>
}