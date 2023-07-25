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
import androidx.compose.ui.unit.dp
import br.com.davidcastro.ui.R
import coil.compose.AsyncImage

@Composable
fun BannerWidget(
    url: String,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .height(300.dp)
            .fillMaxWidth(),
        color = Color.DarkGray,
        shape = AbsoluteRoundedCornerShape(
            bottomRight = 40.dp,
            bottomLeft = 40.dp
        )
    ) {
        AsyncImage(
            model = url,
            contentScale = ContentScale.Crop,
            contentDescription = null,
        )
        Image(
            painter = painterResource(id = R.drawable.background_with_alpha),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .clickable {
                    onClick.invoke()
                }
        )
    }
}

