package br.com.davidcastro.ui.widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.davidcastro.data.model.Photo
import br.com.davidcastro.ui.theme.Dimens.dimen16dp
import br.com.davidcastro.ui.theme.Dimens.dimen8dp
import br.com.davidcastro.ui.theme.Green

@Composable
fun ImageHorizontalListWidget(
    modifier: Modifier = Modifier,
    photos: List<Photo>,
    onItemClick: (selectedIndex: Int) -> Unit
) {
    LazyRow(
        modifier = modifier,
        contentPadding = PaddingValues(dimen8dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        items(10) {
            RoundedImage(
                url = photos[it].src.medium,
                color = photos[it].avgColor,
                modifier = Modifier
                    .height(200.dp)
                    .width(150.dp)
                    .padding(horizontal = dimen8dp)
            ) {
                onItemClick(it)
            }
        }

        item {
            Text(
                text = "Ver mais",
                color = Green,
                modifier = Modifier
                    .padding(dimen16dp)
                    .clickable {
                        onItemClick(11)
                    }
            )
        }
    }
}