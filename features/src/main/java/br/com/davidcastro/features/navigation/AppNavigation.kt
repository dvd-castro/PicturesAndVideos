package br.com.davidcastro.features.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import br.com.davidcastro.features.screens.curated.view.CuratedScreen
import br.com.davidcastro.features.screens.home.view.HomeScreen
import br.com.davidcastro.features.screens.photodetails.data.PhotoDetailState
import br.com.davidcastro.features.screens.photodetails.view.PhotoDetailsScreen
import br.com.davidcastro.ui.utils.extensions.getRouteArgs
import br.com.davidcastro.ui.widgets.ToolbarWidget

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavigation(
    navController: NavHostController = rememberNavController(),
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreenName = backStackEntry?.destination?.route ?: Routes.HomeScreen.name

    Scaffold(
        topBar = {
            ToolbarWidget(
                canNavigateBack = currentScreenName != Routes.HomeScreen.name,
                actionButton = {
                    if(currentScreenName != Routes.HomeScreen.name) {
                        navController.navigateUp()
                    } else {
                        //TODO open menu
                    }
                },
                onSearchClick = {
                    //TODO search
                }
            )
                 },
        content = { paddingValues ->
            val modifier = Modifier.padding(paddingValues)

            NavHost(navController = navController, startDestination = Routes.HomeScreen.name ) {
                composable(Routes.HomeScreen.name) {
                    HomeScreen(modifier, navController)
                }
                composable(Routes.CuratedScreen.name) {
                    CuratedScreen(modifier, navController)
                }
                composable("${Routes.PhotoScreen.name}/{photos}") {
                    val photoDetailState = it.getRouteArgs("photos", PhotoDetailState::class.java)
                    PhotoDetailsScreen(modifier, navController, photoDetailState)
                }
            }
        })
}