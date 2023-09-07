package br.com.davidcastro.ui.widgets

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.davidcastro.data.model.Photo

@Composable
fun ImageHorizontalListWidget(
    modifier: Modifier = Modifier,
    photos: List<Photo>,
    onItemClick: () -> Unit
) {
    LazyRow(
        modifier = modifier,
        contentPadding = PaddingValues(8.dp),
    ) {
        items(10) {
            RoundedImage(
                url = photos[it].src.medium,
                color = photos[it].avgColor,
                modifier = Modifier
                    .height(250.dp)
                    .width(150.dp)
                    .padding(horizontal = 8.dp)
            ) {
                onItemClick()
            }
        }
    }
}