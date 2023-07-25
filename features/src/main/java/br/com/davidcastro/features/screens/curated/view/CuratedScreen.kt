package br.com.davidcastro.features.screens.curated.view

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import br.com.davidcastro.features.navigation.Routes
import br.com.davidcastro.features.screens.photodetails.data.PhotoDetailState
import br.com.davidcastro.features.viewmodel.MainViewModel
import br.com.davidcastro.ui.utils.extensions.navigateWithArgs
import br.com.davidcastro.ui.widgets.ImageListWidget

@Composable
fun CuratedScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    mainViewModel: MainViewModel = hiltViewModel(),
) {
    val curatedState by mainViewModel.curatedState.collectAsState()

    LaunchedEffect(Unit) {
        mainViewModel.getCuratedPhotos(1)
    }

    if(curatedState.photos.isNotEmpty()) {
        ImageListWidget(
            modifier = modifier,
            photos = curatedState.photos,
            loadMore = {
                mainViewModel.getCuratedPhotos(curatedState.nextPage)
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
}