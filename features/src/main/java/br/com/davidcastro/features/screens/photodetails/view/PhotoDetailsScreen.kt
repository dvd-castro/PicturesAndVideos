package br.com.davidcastro.features.screens.photodetails.view

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
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.PhoneAndroid
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import br.com.davidcastro.data.model.Photo
import br.com.davidcastro.features.screens.photodetails.data.PhotoDetailState
import br.com.davidcastro.ui.R
import br.com.davidcastro.ui.theme.Dimens.dimen120dp
import br.com.davidcastro.ui.theme.Dimens.dimen16dp
import br.com.davidcastro.ui.theme.Dimens.dimen2dp
import br.com.davidcastro.ui.theme.Dimens.dimen4dp
import br.com.davidcastro.ui.theme.Dimens.dimen80dp
import br.com.davidcastro.ui.theme.Dimens.dimen8dp
import br.com.davidcastro.ui.theme.Green
import br.com.davidcastro.ui.theme.TopAppBarBackground
import br.com.davidcastro.ui.widgets.BottomOption
import br.com.davidcastro.ui.widgets.RoundedImage
import br.com.davidcastro.ui.widgets.SessionTitleWidget
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage

@Composable
fun PhotoDetailsScreen(
    modifier: Modifier = Modifier,
    photoDetailState: PhotoDetailState
) {

    var selectedIndex by rememberSaveable { mutableIntStateOf(0) }

    ConstraintLayout(modifier.fillMaxSize()) {
        val (mainPhoto, photoList, bottomOptions) = createRefs()

        MainPicture(
            url = photoDetailState.photos[selectedIndex].src.original,
            modifier = Modifier.constrainAs(mainPhoto) {
                top.linkTo(parent.top)
                bottom.linkTo(bottomOptions.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        )

        PhotoPreviewList(
            selectedIndex = selectedIndex,
            photoList = photoDetailState.photos,
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
            selectedPhoto = photoDetailState.photos[selectedIndex]
        )
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
private fun MainPicture(
    modifier: Modifier = Modifier,
    url: String
) {
    GlideImage(
        model = url,
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
                    shape = AbsoluteRoundedCornerShape(dimen16dp),
                    modifier = Modifier.padding(dimen4dp)

                ) {
                    RoundedImage(
                        url = it.src.medium,
                        color = it.avgColor,
                        modifier = modifier
                            .padding(dimen4dp)
                            .height(dimen120dp)
                            .width(dimen80dp),
                    ) {}
                }
            } else {
                RoundedImage(
                    url = it.src.medium,
                    color = it.avgColor,
                    modifier = modifier
                        .padding(dimen4dp)
                        .height(dimen120dp)
                        .width(dimen80dp),
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
    selectedPhoto: Photo
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
                    text = stringResource(id = R.string.option_set_wallpaper),
                    icon = Icons.Outlined.PhoneAndroid
                ) {

                }

                BottomOption(
                    text = stringResource(id = R.string.option_set_lock_screen),
                    icon = Icons.Outlined.Lock
                ) {

                }

                BottomOption(
                    text = stringResource(id = R.string.option_download),
                    icon = Icons.Outlined.Download
                ) {

                }

                BottomOption(
                    text = stringResource(id = R.string.option_share),
                    icon = Icons.Outlined.Share
                ) {

                }
            }
        }
    }
}