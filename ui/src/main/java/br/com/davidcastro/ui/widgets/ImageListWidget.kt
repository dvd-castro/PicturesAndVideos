package br.com.davidcastro.ui.widgets

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.davidcastro.data.model.Photo

@Composable
fun ImageListWidget(
    modifier: Modifier = Modifier,
    photos: List<Photo>,
    loadMore: () -> Unit,
    onItemClick: () -> Unit
) { 
    val listState = rememberLazyGridState()

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier,
        contentPadding = PaddingValues(8.dp),
        state = listState
    ) {
        items(photos) { photo ->
            listState.let {
                if(it.layoutInfo.visibleItemsInfo.isNotEmpty() && (photos.indexOf(photo) == (photos.count() - 1))) {
                    LaunchedEffect(Unit) {
                        loadMore.invoke()
                    }
                } else {
                    RoundedImage(url = photo.src.medium) {
                        onItemClick.invoke()
                    }
                }
            }
        }
    }
}