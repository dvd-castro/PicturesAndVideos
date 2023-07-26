package br.com.davidcastro.ui.widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun RoundedImage(
    modifier: Modifier = Modifier,
    url: String,
    onClick: () -> Unit
) {
    Box(modifier = Modifier) {
        AsyncImage(
            model = url,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = modifier
                .padding(0.dp)
                .clip(AbsoluteRoundedCornerShape(16.dp))
                .clickable {
                    onClick.invoke()
                }
        )
    }
}