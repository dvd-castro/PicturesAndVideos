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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import br.com.davidcastro.data.model.PhotoResponse
import br.com.davidcastro.ui.R
import br.com.davidcastro.ui.theme.Dimens.dimen16dp
import br.com.davidcastro.ui.theme.Dimens.dimen22dp
import br.com.davidcastro.ui.theme.Dimens.dimen44dp
import br.com.davidcastro.ui.theme.Dimens.dimen4dp
import br.com.davidcastro.ui.theme.Dimens.dimen8dp
import br.com.davidcastro.ui.theme.Dimens.size32sp
import br.com.davidcastro.ui.theme.Green

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BannerCarousel(
    response: PhotoResponse,
    onClick: () -> Unit
) {
    val list = response.photos
    val pageCount = 5
    val pagerState = rememberPagerState {
       pageCount
    }

    ConstraintLayout {
        val (banner, bannerPosition, title) = createRefs()

        HorizontalPager(
            state = pagerState,
            modifier = Modifier.constrainAs(banner) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom)
            }
        ) {
            BannerWidget(
                url = list[it].src.original,
                color = list[it].avgColor
            ) {
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
                    CircleWithBorder(borderColor = Green, borderWidth = 1.dp, size = dimen16dp) {
                        ColoredCircle(color = Green, size = dimen8dp, content = {})
                    }
                } else  {
                    CircleWithBorder(borderColor = Green, borderWidth = 1.dp, size = dimen16dp, content = {})
                }
            }
        }

        Row(
            modifier = Modifier
                .padding(dimen22dp)
                .constrainAs(title) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                }
        ) {
            Text(
                text = stringResource(id = R.string.title_recommendations),
                color = Color.White,
                fontSize = size32sp
            )
            Icon(
                imageVector = Icons.Rounded.ArrowForward,
                contentDescription = "",
                tint = Green,
                modifier = Modifier.size(dimen44dp)
            )
        }
    }
}

@Composable
private fun CircleWithBorder(borderColor: Color, borderWidth: Dp, size: Dp, content: @Composable () -> Unit) {
    Box(Modifier.padding(dimen4dp)) {
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