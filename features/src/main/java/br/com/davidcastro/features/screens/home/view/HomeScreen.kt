package br.com.davidcastro.features.screens.home.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import br.com.davidcastro.features.navigation.Routes
import br.com.davidcastro.features.screens.home.viewmodel.HomeViewModel
import br.com.davidcastro.ui.widgets.BannerCarousel
import br.com.davidcastro.ui.widgets.CollectionWidget
import br.com.davidcastro.ui.widgets.ImageHorizontalListWidget
import br.com.davidcastro.ui.widgets.SessionTitleWidget

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    homeViewModel: HomeViewModel = hiltViewModel(),
) {
    val homeState by homeViewModel.homeState.collectAsState()

    LaunchedEffect(Unit) {
        homeViewModel.getCuratedBannerPhotos(1)
        homeViewModel.getPopularPhotos(1)
    }

    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        homeState.bannerState.response?.let {
            BannerCarousel(it) {
                navController.navigate(Routes.CuratedScreen.name)
            }
        }
        SessionTitleWidget(text = "Coleções")
        CollectionWidget()
        SessionTitleWidget(text = "Popular")
        homeState.popularState.response?.let {
            ImageHorizontalListWidget(photos = it.photos) {
                navController.navigate(Routes.PopularScreen.name)
            }
        }
    }
}
