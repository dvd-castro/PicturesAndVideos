package br.com.davidcastro.ui.widgets

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CollectionWidget(
    modifier: Modifier = Modifier
) {
    val list = listOf("Abstract", "Nature", "Universe", "Urban", "City", "Technology", "Science", "Flowers")
    LazyRow(
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = 12.dp)
    ) {
        items(list) {
            Button(
                modifier = modifier.padding(horizontal = 4.dp),
                onClick = { /*TODO*/ }
            ) {
                Text(text = it)
            }
        }
    }
}