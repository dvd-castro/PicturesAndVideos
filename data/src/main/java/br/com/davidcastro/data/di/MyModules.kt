package br.com.davidcastro.data.di

import br.com.davidcastro.data.api.Api
import br.com.davidcastro.data.api.RetrofitClient
import br.com.davidcastro.data.repository.ApiRepository
import br.com.davidcastro.data.repository.ApiRepositoryImpl
import br.com.davidcastro.data.usecase.GetCuratedPhotosUseCase
import br.com.davidcastro.data.usecase.GetCuratedPhotosUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
object MyModule {

    @Provides
    fun provideApi(): Api =
        RetrofitClient.getRetrofitInstance(Api::class.java, "https://api.pexels.com/")

    @Provides
    fun provideApiRepository(api: Api): ApiRepository =
        ApiRepositoryImpl(api)

    @Provides
    fun provideGetCuratedPhotoUseCase(repository: ApiRepository): GetCuratedPhotosUseCase =
        GetCuratedPhotosUseCaseImpl(repository)

}