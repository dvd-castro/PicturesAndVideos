package br.com.davidcastro.data.api

import br.com.davidcastro.data.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val AUTHORIZATION = "Authorization"
    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .addInterceptor { chain ->
            val originalRequest = chain.request()
            val authorizedRequest = originalRequest
                .newBuilder()
                .header(AUTHORIZATION, BuildConfig.API_TOKEN)
                .build()
            chain.proceed(authorizedRequest)
        }
        .build()

    fun <S> getRetrofitInstance(serviceClass: Class<S>, path: String) : S {
        return Retrofit.Builder()
            .baseUrl(path)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(serviceClass)
    }
}