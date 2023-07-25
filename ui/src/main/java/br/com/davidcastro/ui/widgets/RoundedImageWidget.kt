package br.com.davidcastro.ui.widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun RoundedImage(
    modifier: Modifier = Modifier,
    url: String,
    onClick: () -> Unit
) {
    Box(modifier = modifier.padding(8.dp)) {
        Surface(
            modifier = modifier.height(250.dp),
            color = Color.DarkGray,
            shape = AbsoluteRoundedCornerShape(16.dp)
        ) {
            AsyncImage(
                model = url,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = modifier.clickable {
                    onClick.invoke()
                }
            )
        }
    }
}