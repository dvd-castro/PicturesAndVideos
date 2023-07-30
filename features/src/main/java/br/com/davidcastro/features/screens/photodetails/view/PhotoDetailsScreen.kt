package br.com.davidcastro.features.screens.photodetails.view

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Download
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.PhoneAndroid
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import br.com.davidcastro.data.model.Photo
import br.com.davidcastro.features.screens.photodetails.data.PhotoDetailState
import br.com.davidcastro.ui.theme.Green
import br.com.davidcastro.ui.theme.TopAppBarBackground
import br.com.davidcastro.ui.widgets.RoundedImage
import coil.compose.AsyncImage

@Composable
fun PhotoDetailsScreen(
    modifier: Modifier = Modifier,
    photoDetailState: PhotoDetailState
) {

    var selectedIndex by rememberSaveable { mutableStateOf(0) }

    ConstraintLayout(modifier.fillMaxSize()) {
        val (mainPhoto, photoList, bottomOptions) = createRefs()

        MainPicture(
            photo = photoDetailState.photos[selectedIndex],
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

@Composable
private fun MainPicture(
    modifier: Modifier = Modifier,
    photo: Photo
) {
    AsyncImage(
        model = photo.src.large,
        contentDescription = null,
        modifier = modifier,
        contentScale = ContentScale.Crop
    )
}

@Composable
private fun PhotoPreviewList(
    modifier: Modifier = Modifier,
    selectedIndex: Int,
    photoList: List<Photo>,
    onSelectPhoto: (index: Int) -> Unit,
) {
    LazyRow(modifier.padding(bottom = 16.dp)) {
        items(photoList) {
            if (selectedIndex == photoList.indexOf(it)) {
                RectangleWithBorder(
                    borderColor = Green,
                    borderWidth = 2.dp,
                    shape = AbsoluteRoundedCornerShape(16.dp),
                    modifier = Modifier.padding(4.dp)

                ) {
                    RoundedImage(
                        url = it.src.medium,
                        modifier = modifier
                            .padding(4.dp)
                            .height(120.dp)
                            .width(80.dp),
                    ) {}
                }
            } else {
                RoundedImage(
                    url = it.src.medium,
                    modifier = modifier
                        .padding(4.dp)
                        .height(120.dp)
                        .width(80.dp),
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
        shape = AbsoluteRoundedCornerShape(topLeft = 16.dp, topRight = 16.dp)
    ) {
        val scrollState = rememberScrollState()
        Column {
            Text(
                text = "By: ${selectedPhoto.photographer}",
                modifier = Modifier.padding(start = 16.dp, top = 16.dp, end = 16.dp)
            )
            Text(
                text = "Tamanho original: ${selectedPhoto.height} x ${selectedPhoto.width}",
                Modifier.padding(start = 16.dp, top = 8.dp, end = 16.dp)
            )
            Row(
                Modifier
                    .padding(16.dp)
                    .horizontalScroll(scrollState)
            ) {
                Column(
                    modifier = Modifier.width(100.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        imageVector = Icons.Outlined.FavoriteBorder,
                        tint = Green,
                        contentDescription = null,
                        modifier = Modifier
                            .size(28.dp)
                            .padding(bottom = 4.dp)
                    )
                    Text(
                        text = "Favoritar",
                        fontSize = 14.sp,
                        textAlign = TextAlign.Center,
                        lineHeight = 18.sp
                    )
                }

                Column(
                    modifier = Modifier.width(100.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        imageVector = Icons.Outlined.PhoneAndroid,
                        tint = Green,
                        contentDescription = null,
                        modifier = Modifier
                            .size(28.dp)
                            .padding(bottom = 4.dp)
                    )
                    Text(
                        text = "Definir como tela de fundo",
                        fontSize = 14.sp,
                        textAlign = TextAlign.Center,
                        lineHeight = 18.sp
                    )
                }

                Column(
                    modifier = Modifier.width(100.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Lock,
                        tint = Green,
                        contentDescription = null,
                        modifier = Modifier
                            .size(28.dp)
                            .padding(bottom = 4.dp)
                    )
                    Text(
                        text = "Definir como tela de bloqueio",
                        fontSize = 14.sp,
                        textAlign = TextAlign.Center,
                        lineHeight = 18.sp
                    )
                }

                Column(
                    modifier = Modifier.width(100.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Download,
                        tint = Green,
                        contentDescription = null,
                        modifier = Modifier
                            .size(28.dp)
                            .padding(bottom = 4.dp)
                    )
                    Text(
                        text = "Download",
                        fontSize = 14.sp,
                        textAlign = TextAlign.Center,
                        lineHeight = 18.sp
                    )
                }

                Column(
                    modifier = Modifier.width(100.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Share,
                        tint = Green,
                        contentDescription = null,
                        modifier = Modifier
                            .size(28.dp)
                            .padding(bottom = 4.dp)
                    )
                    Text(
                        text = "Compartilhar",
                        fontSize = 14.sp,
                        textAlign = TextAlign.Center,
                        lineHeight = 18.sp
                    )
                }
            }
        }
    }
}