package br.com.davidcastro.ui.widgets

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun RoundedImage(
    modifier: Modifier = Modifier,
    url: String
) {
    Box(modifier = modifier.padding(8.dp)) {
        Surface(
            modifier = modifier
                .clip(AbsoluteRoundedCornerShape(16.dp))
                .height(250.dp),
            color = Color.DarkGray
        ) {
            AsyncImage(
                model = url,
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
        }
    }
}