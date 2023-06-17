package br.com.davidcastro.features.screens.home.view

import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import br.com.davidcastro.ui.widgets.BannerWidget

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
) {
    Carousel()
}

@Composable
private fun Carousel() {
    val list = listOf(1,2,3,4)
    LazyRow {
        items(list) {item ->
            BannerWidget()
        }
    }
}