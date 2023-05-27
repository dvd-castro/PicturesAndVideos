package br.com.davidcastro.data.api

import br.com.davidcastro.data.model.PhotoResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

private const val DEFAULT_PER_PAGE = 80

interface Api {
    @GET("/v1/curated")
    fun getCuratedPhotos(
        @Query("per_page") perPage: Int = DEFAULT_PER_PAGE
    ): Response<PhotoResponse>

    @GET("/v1/popular")
    fun getPopularPhotos(
        @Query("per_page") perPage: Int = DEFAULT_PER_PAGE
    ): Response<PhotoResponse>
}