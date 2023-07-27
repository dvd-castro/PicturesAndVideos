package br.com.davidcastro.ui.widgets

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import br.com.davidcastro.ui.theme.Gray

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
                shape = AbsoluteRoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Gray, contentColor = Color.White),
                onClick = { /*TODO*/ }
            ) {
                Text(text = it)
            }
        }
    }
}