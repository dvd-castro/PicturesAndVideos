package br.com.davidcastro.ui.widgets

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.davidcastro.data.model.Photo

@Composable
fun ImageListWidget(
    modifier: Modifier = Modifier,
    photos: List<Photo>,
    loadMore: (page: Int) -> Unit,
    onItemClick: () -> Unit
) {
    var currentPage by rememberSaveable { mutableStateOf(1) }
    val listState = rememberLazyGridState()

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier,
        contentPadding = PaddingValues(8.dp),
        state = listState
    ) {
        items(photos) {
            if(photos.indexOf(it) == photos.count() - 1) {
                LaunchedEffect(Unit) {
                    loadMore.invoke(currentPage)
                }
                currentPage+=1
            } else {
                RoundedImage(url = it.src.medium) {
                    onItemClick.invoke()
                }
            }
        }
    }
}