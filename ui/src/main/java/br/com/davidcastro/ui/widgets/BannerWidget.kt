package br.com.davidcastro.ui.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import br.com.davidcastro.ui.R
import br.com.davidcastro.ui.theme.Dimens.dimen300dp
import br.com.davidcastro.ui.theme.Dimens.dimen44dp
import br.com.davidcastro.ui.utils.extensions.toShapeDrawable
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun BannerWidget(
    url: String,
    color: String,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .height(dimen300dp)
            .fillMaxWidth(),
        color = Color.DarkGray,
        shape = AbsoluteRoundedCornerShape(
            bottomRight = dimen44dp,
            bottomLeft = dimen44dp
        )
    ) {
        GlideImage(
            model = url,
            contentScale = ContentScale.Crop,
            contentDescription = null,
            loading = placeholder(color.toShapeDrawable())
        )
        Image(
            painter = painterResource(id = R.drawable.background_with_alpha),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .clickable {
                    onClick()
                }
        )
    }
}

