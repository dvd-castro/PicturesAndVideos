package br.com.davidcastro.ui.widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import br.com.davidcastro.ui.theme.Dimens.dimen16dp
import br.com.davidcastro.ui.utils.extensions.toShapeDrawable
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun RoundedImage(
    modifier: Modifier = Modifier,
    url: String,
    color: String,
    onClick: () -> Unit
) {
    GlideImage(
        model = url,
        contentDescription = null,
        contentScale = ContentScale.Crop,
        loading = placeholder(color.toShapeDrawable()),
        modifier = modifier
            .padding(0.dp)
            .clip(AbsoluteRoundedCornerShape(dimen16dp))
            .clickable {
                onClick()
            }
    )
}