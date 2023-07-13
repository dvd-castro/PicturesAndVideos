package br.com.davidcastro.features.screens.curated.view

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import br.com.davidcastro.features.viewmodel.MainViewModel
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
            photos = curatedState.photos) {
            mainViewModel.getCuratedPhotos(page = it)
        }
    }
}