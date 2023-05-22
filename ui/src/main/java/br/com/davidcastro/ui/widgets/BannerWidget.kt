package br.com.davidcastro.ui.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.davidcastro.ui.R

@Preview
@Composable
private fun BannerWidgetPreview() {
    BannerWidget()
}

@Composable
fun BannerWidget() {
    Surface(
        modifier = Modifier
            .clip(
                AbsoluteRoundedCornerShape(
                    bottomRight = 40.dp,
                    bottomLeft = 40.dp
                )
            )
            .height(300.dp)
            .fillMaxWidth(),
        color = Color.DarkGray
    ) {
        Image(
            painter = painterResource(id = R.drawable.sample_banner),
            contentDescription = "",
            contentScale = ContentScale.Crop
        )
        Image(
            painter = painterResource(id = R.drawable.background_with_alpha),
            contentDescription = "",
            contentScale = ContentScale.Crop
        )
        Row(
            modifier = Modifier.padding(20.dp)
        ) {
            Text(
                text = "Destaques",
                color = Color.White,
                fontSize = 32.sp
            )
            Icon(
                imageVector = Icons.Rounded.ArrowForward,
                contentDescription = "",
                tint = Color.White,
                modifier = Modifier.size(46.dp)
            )
        }
    }
}

