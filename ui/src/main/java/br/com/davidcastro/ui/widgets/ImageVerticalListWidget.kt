package br.com.davidcastro.ui.widgets

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import br.com.davidcastro.data.model.Photo
import br.com.davidcastro.ui.theme.Dimens.dimen250dp
import br.com.davidcastro.ui.theme.Dimens.dimen8dp

@Composable
fun ImageVerticalListWidget(
    modifier: Modifier = Modifier,
    photos: List<Photo>,
    loadMore: () -> Unit,
    onItemClick: (index: Int) -> Unit
) { 
    val listState = rememberLazyGridState()

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier,
        contentPadding = PaddingValues(dimen8dp),
        state = listState
    ) {
        items(photos) { photo ->
            listState.let {
                if(it.layoutInfo.visibleItemsInfo.isNotEmpty() && (photos.indexOf(photo) == (photos.count() - 1))) {
                    LaunchedEffect(Unit) {
                        loadMore()
                    }
                } else {
                    RoundedImage(
                        url = photo.src.medium,
                        color = photo.avgColor,
                        modifier = Modifier
                            .height(dimen250dp)
                            .padding(dimen8dp)
                    ) {
                        onItemClick(photos.indexOf(photo))
                    }
                }
            }
        }
    }
}