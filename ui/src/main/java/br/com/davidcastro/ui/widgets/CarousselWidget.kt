package br.com.davidcastro.ui.widgets

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import br.com.davidcastro.data.model.PhotoResponse
import br.com.davidcastro.ui.theme.Green

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BannerCarousel(
    response: PhotoResponse,
    onClick: () -> Unit
) {
    val pagerState = rememberPagerState()
    val list = response.photos
    val pageCount = 5

    ConstraintLayout {
        val (banner, bannerPosition, title) = createRefs()

        HorizontalPager(
            state = pagerState,
            pageCount = pageCount,
            modifier = Modifier.constrainAs(banner) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom)
            }
        ) { position ->
            BannerWidget(url = list[position].src.original) {
               onClick()
            }
        }

        LazyRow(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .constrainAs(bannerPosition) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                },
        ) {
            items(pageCount) {
                if(pagerState.currentPage == it) {
                    CircleWithBorder(borderColor = Green, borderWidth = 1.dp, size = 16.dp) {
                        ColoredCircle(color = Green, size = 8.dp, content = {})
                    }
                } else  {
                    CircleWithBorder(borderColor = Green, borderWidth = 1.dp, size = 16.dp, content = {})
                }
            }
        }

        Row(
            modifier = Modifier
                .padding(20.dp)
                .constrainAs(title) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                }
        ) {
            Text(
                text = "Destaques",
                color = Color.White,
                fontSize = 32.sp
            )
            Icon(
                imageVector = Icons.Rounded.ArrowForward,
                contentDescription = "",
                tint = Green,
                modifier = Modifier.size(46.dp)
            )
        }
    }
}



@Composable
private fun CircleWithBorder(borderColor: Color, borderWidth: Dp, size: Dp, content: @Composable () -> Unit) {
    Box(Modifier.padding(4.dp)) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(size)
                .clip(CircleShape)
                .border(width = borderWidth, color = borderColor, shape = CircleShape)
                .background(color = Color.Transparent),
        ) {
            content()
        }
    }
}

@Composable
private fun ColoredCircle(color: Color, size: Dp, content: @Composable () -> Unit) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(size)
            .clip(CircleShape)
            .background(color),
    ) {
        content()
    }
}