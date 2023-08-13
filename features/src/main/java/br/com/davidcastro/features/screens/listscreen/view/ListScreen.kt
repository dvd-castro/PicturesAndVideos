package br.com.davidcastro.features.screens.listscreen.view

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import br.com.davidcastro.features.navigation.Routes
import br.com.davidcastro.features.screens.listscreen.model.ListScreenArgs
import br.com.davidcastro.features.screens.listscreen.model.ListScreenType
import br.com.davidcastro.features.screens.listscreen.viewmodel.ListViewModel
import br.com.davidcastro.features.screens.photodetails.data.PhotoDetailState
import br.com.davidcastro.ui.utils.extensions.navigateWithArgs
import br.com.davidcastro.ui.widgets.ImageVerticalListWidget
import br.com.davidcastro.ui.widgets.LoaderVerticalImageListWidget

@Composable
fun PhotoListScreen(
    modifier: Modifier = Modifier,
    listViewModel: ListViewModel = hiltViewModel(),
    navController: NavHostController,
    args: ListScreenArgs,
) {
    val photoListState by listViewModel.listScreenState.collectAsState()

    LaunchedEffect(Unit) {
        when(args.type) {
            ListScreenType.RECOMMENDATION -> {
                listViewModel.getCuratedPhotos(1)
            }
            ListScreenType.POPULAR -> {
                listViewModel.getPopularPhotos(1)
            }
            else -> {
                //TODO Search
            }
        }
    }

    Column {
        if(photoListState.photos.isNotEmpty()) {
            ImageVerticalListWidget(
                modifier = modifier,
                photos = photoListState.photos,
                loadMore = {
                    if(!photoListState.hasEnd) {
                        listViewModel.getCuratedPhotos(photoListState.nextPage)
                    }
                },
                onItemClick = { selectedIndex ->
                    navController.navigateWithArgs(
                        Routes.PhotoDetailScreen.route,
                        PhotoDetailState(
                            selectedIndex = selectedIndex,
                            photos = photoListState.photos.filterIndexed { index, _ ->
                                index == selectedIndex || index == selectedIndex + 1 || index == selectedIndex + 2
                            }
                        )
                    )
                }
            )
        }

        if(photoListState.isLoading && photoListState.nextPage == 1) {
            LoaderVerticalImageListWidget(modifier)
        }
    }
}