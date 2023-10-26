package br.com.davidcastro.data.usecase.downloadimageusecase

import android.content.Context
import android.graphics.Bitmap
import com.bumptech.glide.Glide
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetImageBitmapUseCaseImpl(private val context: Context): GetImageBitmapUseCase {
    override suspend operator fun invoke(url: String): Bitmap  = withContext(Dispatchers.IO) {
        return@withContext Glide.with(context).asBitmap().load(url).submit().get()
    }
}