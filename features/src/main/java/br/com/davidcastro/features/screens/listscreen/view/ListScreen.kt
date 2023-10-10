package br.com.davidcastro.features.screens.listscreen.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import br.com.davidcastro.features.navigation.Routes
import br.com.davidcastro.features.screens.listscreen.model.ListScreenArgs
import br.com.davidcastro.features.screens.listscreen.model.ListScreenType
import br.com.davidcastro.features.screens.listscreen.state.ListScreenState
import br.com.davidcastro.features.screens.listscreen.viewmodel.ListViewModel
import br.com.davidcastro.features.screens.photodetails.data.PhotoDetailState
import br.com.davidcastro.ui.utils.extensions.navigateWithArgs
import br.com.davidcastro.ui.widgets.ImageVerticalListWidget
import br.com.davidcastro.ui.widgets.LoaderVerticalImageListWidget

private const val INITIAL_PAGE = 1

@Composable
fun PhotoListScreen(
    modifier: Modifier = Modifier,
    listViewModel: ListViewModel = hiltViewModel(),
    navController: NavHostController,
    args: ListScreenArgs,
) {
    val photoListState by listViewModel.listScreenState.collectAsState()

    fun getMore() {
        when(args.type) {
            ListScreenType.RECOMMENDATION -> {
                listViewModel.getCuratedPhotos(photoListState.nextPage)
            }
            ListScreenType.POPULAR -> {
                listViewModel.getPopularPhotos(photoListState.nextPage)
            }
            else -> {
                listViewModel.getSearchPhotos(args.search, photoListState.nextPage)
            }
        }
    }

    LaunchedEffect(Unit) {
        getMore()
    }

    when {
        photoListState.isLoading && photoListState.nextPage == INITIAL_PAGE -> {
            LoaderVerticalImageListWidget(modifier)
        }

        photoListState.photos.isNotEmpty() -> {
            PhotoListScreen(
                modifier,
                photoListState,
                navController
            ) {
                getMore()
            }
        }
        photoListState.resultNotFound -> {
            NotFoundError(modifier)
        }
    }
}

@Composable
fun PhotoListScreen(
    modifier: Modifier,
    photoListState: ListScreenState,
    navController: NavHostController,
    getMore: () -> Unit
) {
    Column(modifier) {
        ImageVerticalListWidget(
            modifier = Modifier,
            photos = photoListState.photos,
            loadMore = {
                if(!photoListState.hasEnd) {
                    getMore()
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
}

@Composable
fun NotFoundError(
    modifier: Modifier
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Nenhum resultado encontrado!")
    }
}