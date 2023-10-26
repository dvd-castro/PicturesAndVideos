package br.com.davidcastro.features.screens.photodetails.view

import android.app.DownloadManager
import android.app.WallpaperManager
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Environment
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Download
import androidx.compose.material.icons.outlined.Fullscreen
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.PhoneAndroid
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.core.content.FileProvider
import androidx.hilt.navigation.compose.hiltViewModel
import br.com.davidcastro.data.model.Photo
import br.com.davidcastro.features.screens.photodetails.data.PhotoDetail
import br.com.davidcastro.features.screens.photodetails.viewmodel.PhotoDetailsViewModel
import br.com.davidcastro.ui.R
import br.com.davidcastro.ui.theme.Dimens.dimen16dp
import br.com.davidcastro.ui.theme.Dimens.dimen2dp
import br.com.davidcastro.ui.theme.Dimens.dimen4dp
import br.com.davidcastro.ui.theme.Dimens.dimen52dp
import br.com.davidcastro.ui.theme.Dimens.dimen80dp
import br.com.davidcastro.ui.theme.Dimens.dimen8dp
import br.com.davidcastro.ui.theme.Green
import br.com.davidcastro.ui.theme.TopAppBarBackground
import br.com.davidcastro.ui.utils.extensions.doIfTrue
import br.com.davidcastro.ui.widgets.BottomOption
import br.com.davidcastro.ui.widgets.RoundedImage
import br.com.davidcastro.ui.widgets.SessionTitleWidget
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import java.io.File
import java.io.FileOutputStream

private const val MIME_TYPE = "image/*"
private const val FILE_PROVIDER = "br.com.davidcastro.picturesandvideos.fileprovider"

@Composable
fun PhotoDetailsScreen(
    modifier: Modifier = Modifier,
    photoDetail: PhotoDetail,
    context: Context = LocalContext.current,
    photoDetailsViewModel: PhotoDetailsViewModel = hiltViewModel()
) {

    val photoDetailsState by photoDetailsViewModel.photoDetailsState.collectAsState()
    var selectedIndex by rememberSaveable { mutableIntStateOf(0) }

    LaunchedEffect(Unit) {
        photoDetailsViewModel.initPhotoDetailsState(photoDetail)
    }

    photoDetailsState.photos.isNotEmpty().doIfTrue {

        LaunchedEffect(photoDetailsState.photos[selectedIndex]) {
            photoDetailsViewModel.getImageBitmap(photoDetailsState.photos[selectedIndex].src.portrait)
        }

        ConstraintLayout(modifier.fillMaxSize()) {
            val (mainPhoto, photoList, bottomOptions) = createRefs()

            photoDetailsState.selectedPhotoBitmap?.let {
                MainPicture(
                    modifier = Modifier.constrainAs(mainPhoto) {
                        top.linkTo(parent.top)
                        bottom.linkTo(bottomOptions.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
                    photo = it,
                )
            }

            PhotoPreviewList(
                selectedIndex = selectedIndex,
                photoList = photoDetailsState.photos,
                modifier = Modifier.constrainAs(photoList) {
                    bottom.linkTo(bottomOptions.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
            ) {
                selectedIndex = it
            }

            BottomOptions(
                modifier = Modifier.constrainAs(bottomOptions) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                },
                selectedPhoto = photoDetailsState.photos[selectedIndex],
                context = context,
                photo = photoDetailsState.selectedPhotoBitmap
            )
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
private fun MainPicture(
    modifier: Modifier = Modifier,
    photo: Bitmap,
) {
    GlideImage(
        model = photo,
        contentDescription = null,
        modifier = modifier,
        contentScale = ContentScale.Crop,
    )
}

@Composable
private fun PhotoPreviewList(
    modifier: Modifier = Modifier,
    selectedIndex: Int,
    photoList: List<Photo>,
    onSelectPhoto: (index: Int) -> Unit,
) {
    LazyRow(modifier.padding(bottom = dimen16dp)) {
        items(photoList) {
            if (selectedIndex == photoList.indexOf(it)) {
                RectangleWithBorder(
                    borderColor = Green,
                    borderWidth = dimen2dp,
                    shape = AbsoluteRoundedCornerShape(dimen8dp),
                    modifier = Modifier.padding(dimen4dp)
                ) {
                    RoundedImage(
                        url = it.src.medium,
                        color = it.avgColor,
                        roundedCorner = dimen8dp,
                        modifier = modifier
                            .padding(dimen4dp)
                            .height(dimen80dp)
                            .width(dimen52dp),
                    ) {}
                }
            } else {
                RoundedImage(
                    url = it.src.medium,
                    color = it.avgColor,
                    roundedCorner = dimen8dp,
                    modifier = modifier
                        .padding(dimen4dp)
                        .height(dimen80dp)
                        .width(dimen52dp),
                ) {
                    onSelectPhoto(photoList.indexOf(it))
                }
            }
        }
    }
}

@Composable
private fun RectangleWithBorder(
    modifier: Modifier = Modifier,
    borderColor: Color,
    borderWidth: Dp,
    shape: Shape,
    content: @Composable () -> Unit
) {
    Box(modifier) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .clip(shape)
                .border(width = borderWidth, color = borderColor, shape = shape)
                .background(color = Color.Transparent),
        ) {
            content()
        }
    }
}

@Composable
private fun BottomOptions(
    modifier: Modifier = Modifier,
    selectedPhoto: Photo,
    photo: Bitmap?,
    context: Context,
) {
    Surface(
        modifier = modifier,
        color = TopAppBarBackground,
        shape = AbsoluteRoundedCornerShape(topLeft = dimen16dp, topRight = dimen16dp)
    ) {
        val scrollState = rememberScrollState()
        Column {
            SessionTitleWidget(
                text = stringResource(id = R.string.title_by, selectedPhoto.photographer),
                modifier =  Modifier.padding(start = dimen16dp, top = dimen16dp, end = dimen16dp)
            )
            SessionTitleWidget(
                text = stringResource(id = R.string.title_original_size, selectedPhoto.height,selectedPhoto.width),
                modifier = Modifier.padding(start = dimen16dp, top = dimen8dp, end = dimen16dp)
            )
            Row(
                Modifier
                    .padding(dimen16dp)
                    .horizontalScroll(scrollState)
            ) {
                BottomOption(
                    text = stringResource(id = R.string.option_view_in_full_screen),
                    icon = Icons.Outlined.Fullscreen
                ) {

                }

                BottomOption(
                    text = stringResource(id = R.string.option_set_wallpaper),
                    icon = Icons.Outlined.PhoneAndroid
                ) {
                    setWallpaper(
                        context = context,
                        photo = photo,
                        isHomeWallpaper = true
                    )
                }

                BottomOption(
                    text = stringResource(id = R.string.option_set_lock_screen),
                    icon = Icons.Outlined.Lock
                ) {
                    setWallpaper(
                        context = context,
                        photo = photo,
                        isHomeWallpaper = false
                    )
                }

                BottomOption(
                    text = stringResource(id = R.string.option_download),
                    icon = Icons.Outlined.Download
                ) {
                    downloadImage(
                        context = context,
                        url = selectedPhoto.src.original,
                        name = selectedPhoto.id.toString()
                    )
                }

                BottomOption(
                    text = stringResource(id = R.string.option_share),
                    icon = Icons.Outlined.Share
                ) {
                    shareImage(
                        context = context,
                        selectedPhoto = selectedPhoto,
                        photo = photo
                    )
                }
            }
        }
    }
}

private fun shareImage(
    context: Context,
    selectedPhoto: Photo,
    photo: Bitmap?
) {
    val cachePath = File(context.cacheDir, context.getString(R.string.cache_dir))
    cachePath.mkdirs()

    val imageFile = File(cachePath, context.getString(R.string.file_name, selectedPhoto.id.toString()))
    val stream = FileOutputStream(imageFile)
    photo?.compress(Bitmap.CompressFormat.JPEG, 100, stream)
    stream.close()

    val uri = FileProvider.getUriForFile(context, FILE_PROVIDER, imageFile)
    val shareIntent = Intent(Intent.ACTION_SEND)
    shareIntent.type = MIME_TYPE
    shareIntent.putExtra(Intent.EXTRA_STREAM, uri)
    shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)

    context.startActivity(Intent.createChooser(shareIntent, context.getString(R.string.share_image)))
}

private fun setWallpaper(context: Context, photo: Bitmap?, isHomeWallpaper: Boolean) {
    val wallpaperManager = WallpaperManager.getInstance(context)
    try {
        val wallpaperDrawable = BitmapDrawable(context.resources, photo)
        wallpaperManager.setBitmap(
            wallpaperDrawable.bitmap,
            null,
            true,
            if(isHomeWallpaper) WallpaperManager.FLAG_SYSTEM
            else WallpaperManager.FLAG_LOCK
        )

        if(isHomeWallpaper) {
            showToast(context, R.string.set_wallpaper_successful)
        } else {
            showToast(context, R.string.set_wallpaper_lockscreen_successful)
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

private fun downloadImage(context: Context, url: String, name: String) {
    val downloadManager = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
    val request = DownloadManager.Request(Uri.parse(url))
        .setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI or DownloadManager.Request.NETWORK_MOBILE)
        .setAllowedOverRoaming(false)
        .setMimeType(MIME_TYPE)
        .setTitle(context.getString(R.string.download_title))
        .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
        .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "image-${name}.jpg")

    downloadManager.enqueue(request)
}

private fun showToast(context: Context, string: Int) {
    Toast.makeText(context, string, Toast.LENGTH_LONG).show()
}