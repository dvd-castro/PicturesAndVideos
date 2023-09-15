package br.com.davidcastro.features.screens.home.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import br.com.davidcastro.features.navigation.Routes
import br.com.davidcastro.features.screens.home.viewmodel.HomeViewModel
import br.com.davidcastro.features.screens.listscreen.model.ListScreenArgs
import br.com.davidcastro.features.screens.listscreen.model.ListScreenType
import br.com.davidcastro.ui.R
import br.com.davidcastro.ui.theme.Dimens.dimen16dp
import br.com.davidcastro.ui.utils.extensions.doIfFalse
import br.com.davidcastro.ui.utils.extensions.doIfTrue
import br.com.davidcastro.ui.utils.extensions.navigateWithArgs
import br.com.davidcastro.ui.widgets.BannerCarousel
import br.com.davidcastro.ui.widgets.CollectionWidget
import br.com.davidcastro.ui.widgets.ImageHorizontalListWidget
import br.com.davidcastro.ui.widgets.SessionTitleWidget

private const val INITIAL_PAGE = 1

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    homeViewModel: HomeViewModel = hiltViewModel(),
) {
    val homeState by homeViewModel.homeState.collectAsState()
    val scrollState = rememberScrollState()

    LaunchedEffect(Unit) {
        if(homeState.popularResponse == null && homeState.bannerResponse == null) {
            homeViewModel.getCuratedBannerPhotos(INITIAL_PAGE)
        }
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .verticalScroll(scrollState)
    ) {
        homeState.hasLoading.doIfTrue {

        }.doIfFalse {
            homeState.bannerResponse?.let {
                BannerCarousel(it) {
                    navController.navigateWithArgs(
                        route = Routes.PhotoListScreen.route,
                        args = ListScreenArgs(type = ListScreenType.RECOMMENDATION)
                    )
                }
            }

            homeState.popularResponse?.let {
                SessionTitleWidget(text = stringResource(id = R.string.session_title_collections), Modifier.padding(dimen16dp))
                CollectionWidget()
                SessionTitleWidget(text = stringResource(id = R.string.session_title_popular), Modifier.padding(dimen16dp))
                ImageHorizontalListWidget(
                    photos = it.photos,
                    onItemClick = {
                        navController.navigateWithArgs(
                            route = Routes.PhotoListScreen.route,
                            args = ListScreenArgs(type = ListScreenType.POPULAR)
                        )
                    }
                )
            }
        }
    }
}
