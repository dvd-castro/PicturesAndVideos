package br.com.davidcastro.ui.widgets

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.davidcastro.ui.theme.Gray
import com.valentinilk.shimmer.shimmer

@Composable
fun LoaderVerticalImageListWidget(
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier.padding(8.dp).shimmer()
    ) {
        items(8) {
            Surface(
                shape = AbsoluteRoundedCornerShape(16.dp),
                modifier = Modifier.height(250.dp).padding(8.dp),
                color = Gray
            ) {}
        }
    }
}