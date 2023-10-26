package br.com.davidcastro.data.di

import android.content.Context
import br.com.davidcastro.data.BuildConfig
import br.com.davidcastro.data.api.Api
import br.com.davidcastro.data.api.RetrofitClient
import br.com.davidcastro.data.repository.ApiRepository
import br.com.davidcastro.data.repository.ApiRepositoryImpl
import br.com.davidcastro.data.usecase.downloadimageusecase.GetImageBitmapUseCase
import br.com.davidcastro.data.usecase.downloadimageusecase.GetImageBitmapUseCaseImpl
import br.com.davidcastro.data.usecase.getcuratedphotosusecase.GetCuratedPhotosUseCase
import br.com.davidcastro.data.usecase.getcuratedphotosusecase.GetCuratedPhotosUseCaseImpl
import br.com.davidcastro.data.usecase.getpopularphotosusecase.GetPopularPhotosUseCase
import br.com.davidcastro.data.usecase.getpopularphotosusecase.GetPopularPhotosUseCaseImpl
import br.com.davidcastro.data.usecase.getsearchphotosusecase.GetSearchPhotosUseCase
import br.com.davidcastro.data.usecase.getsearchphotosusecase.GetSearchPhotosUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class MyModule {

    @Singleton
    @Provides
    fun provideApi(): Api =
        RetrofitClient.getRetrofitInstance(Api::class.java, BuildConfig.BASE_URL)

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

    @Singleton
    @Provides
    fun provideGetSearchPhotoUseCase(
        repository: ApiRepository
    ): GetSearchPhotosUseCase =
        GetSearchPhotosUseCaseImpl(repository)

    @Singleton
    @Provides
    fun GetImageBitmapUseCase(
        @ApplicationContext context: Context
    ): GetImageBitmapUseCase =
        GetImageBitmapUseCaseImpl(context)
}