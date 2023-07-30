package br.com.davidcastro.data.di

import br.com.davidcastro.data.api.Api
import br.com.davidcastro.data.api.RetrofitClient
import br.com.davidcastro.data.repository.ApiRepository
import br.com.davidcastro.data.repository.ApiRepositoryImpl
import br.com.davidcastro.data.usecase.GetCuratedPhotosUseCase
import br.com.davidcastro.data.usecase.GetCuratedPhotosUseCaseImpl
import br.com.davidcastro.data.usecase.GetPopularPhotosUseCase
import br.com.davidcastro.data.usecase.GetPopularPhotosUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class MyModule {

    @Singleton
    @Provides
    fun provideApi(): Api =
        RetrofitClient.getRetrofitInstance(Api::class.java, "https://api.pexels.com/")

    @Singleton
    @Provides
    fun provideApiRepository(
        api: Api
    ): ApiRepository =
        ApiRepositoryImpl(api)

    @Singleton
    @Provides
    fun provideGetCuratedPhotoUseCase(
        repository: ApiRepository
    ): GetCuratedPhotosUseCase =
        GetCuratedPhotosUseCaseImpl(repository)

    @Singleton
    @Provides
    fun provideGetPopularPhotoUseCase(
        repository: ApiRepository
    ): GetPopularPhotosUseCase =
        GetPopularPhotosUseCaseImpl(repository)
}