package br.com.davidcastro.features.screens.popular.view

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import br.com.davidcastro.features.navigation.Routes
import br.com.davidcastro.features.screens.photodetails.data.PhotoDetailState
import br.com.davidcastro.features.screens.popular.viewmodel.PopularViewModel
import br.com.davidcastro.ui.utils.extensions.navigateWithArgs
import br.com.davidcastro.ui.widgets.ImageVerticalListWidget
import br.com.davidcastro.ui.widgets.LoaderVerticalImageListWidget

@Composable
fun PopularScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    curatedViewModel: PopularViewModel = hiltViewModel(),
) {
    val curatedState by curatedViewModel.popularState.collectAsState()

    LaunchedEffect(Unit) {
        curatedViewModel.getPopularPhotos(1)
    }

    Column {
        if(curatedState.photos.isNotEmpty()) {
            ImageVerticalListWidget(
                modifier = modifier,
                photos = curatedState.photos,
                loadMore = {
                    if(!curatedState.hasEnd) {
                        curatedViewModel.getPopularPhotos(curatedState.nextPage)
                    }
                },
                onItemClick = { selectedIndex ->
                    navController.navigateWithArgs(
                        Routes.PhotoScreen.name,
                        PhotoDetailState(
                            selectedIndex = selectedIndex,
                            photos = curatedState.photos.filterIndexed { index, _ ->
                                index == selectedIndex || index == selectedIndex + 1 || index == selectedIndex + 2
                            }
                        )
                    )
                }
            )
        }

        if(curatedState.isLoading && curatedState.nextPage == 1) {
            LoaderVerticalImageListWidget(modifier)
        }
    }
}