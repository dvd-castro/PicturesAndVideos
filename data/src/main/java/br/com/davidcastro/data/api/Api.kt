package br.com.davidcastro.data.api

import br.com.davidcastro.data.model.PhotoResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

private const val DEFAULT_PER_PAGE = 80
private const val DEFAULT_PAGE = 1

interface Api {
    @GET("/v1/curated")
    suspend fun getCuratedPhotos(
        @Query("page") page: Int = DEFAULT_PAGE,
        @Query("per_page") perPage: Int = DEFAULT_PER_PAGE
    ): Response<PhotoResponse>

    @GET("/v1/popular")
    suspend fun getPopularPhotos(
        @Query("page") page: Int = DEFAULT_PAGE,
        @Query("per_page") perPage: Int = DEFAULT_PER_PAGE
    ): Response<PhotoResponse>
}