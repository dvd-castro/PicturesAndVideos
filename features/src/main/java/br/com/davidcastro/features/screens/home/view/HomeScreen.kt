package br.com.davidcastro.features.screens.home.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import br.com.davidcastro.features.navigation.Routes
import br.com.davidcastro.features.viewmodel.MainViewModel
import br.com.davidcastro.ui.widgets.BannerCarousel
import br.com.davidcastro.ui.widgets.CollectionWidget
import br.com.davidcastro.ui.widgets.SessionTitleWidget

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    mainViewModel: MainViewModel = hiltViewModel(),
) {
    val homeState by mainViewModel.homeState.collectAsState()

    LaunchedEffect(Unit) {
        mainViewModel.getCuratedBannerPhotos(1)
    }

    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        homeState.bannerState.response?.let {
            BannerCarousel(it) {
                navController.navigate(Routes.CuratedScreen.name)
            }
        }
        SessionTitleWidget(
            text = "Coleções",
            modifier = Modifier.padding(16.dp)
        )
        CollectionWidget()
    }
}
