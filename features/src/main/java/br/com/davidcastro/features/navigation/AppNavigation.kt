package br.com.davidcastro.features.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import br.com.davidcastro.features.screens.home.view.HomeScreen
import br.com.davidcastro.features.screens.listscreen.model.ListScreenArgs
import br.com.davidcastro.features.screens.listscreen.model.ListScreenType
import br.com.davidcastro.features.screens.listscreen.view.PhotoListScreen
import br.com.davidcastro.features.screens.photodetails.data.PhotoDetail
import br.com.davidcastro.features.screens.photodetails.view.PhotoDetailsScreen
import br.com.davidcastro.ui.theme.ScreenBackground
import br.com.davidcastro.ui.utils.extensions.getRouteArgs
import br.com.davidcastro.ui.utils.extensions.navigateWithArgs
import br.com.davidcastro.ui.widgets.ToolbarWidget

private const val DATA = "data"

@Composable
fun AppNavigation(
    navController: NavHostController = rememberNavController(),
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreenName = backStackEntry?.destination?.route ?: Routes.HomeScreen.name

    Scaffold(
        containerColor = ScreenBackground,
        topBar = {
            ToolbarWidget(
                canNavigateBack = currentScreenName != Routes.HomeScreen.route,
                actionButton = { isBackAction ->
                    if(isBackAction) {
                        navController.navigateUp()
                    } else {
                        //TODO open menu
                    }
                },
                onSearchClick = {
                    navController.navigateWithArgs(
                        route = Routes.PhotoListScreen.route,
                        args = ListScreenArgs(type = ListScreenType.SEARCH, search = it)
                    )
                }
            )
                 },
        content = { paddingValues ->
            val modifier = Modifier.padding(paddingValues)

            NavHost(navController = navController, startDestination = Routes.HomeScreen.route) {
                composable(Routes.HomeScreen.route) {
                    HomeScreen(modifier, navController)
                }

                composable(Routes.PhotoListScreen.routeWithArgs) {
                    val args = it.getRouteArgs(DATA, ListScreenArgs::class.java)
                    PhotoListScreen(
                        modifier = modifier,
                        navController = navController,
                        args = args
                    )
                }

                composable(Routes.PhotoDetailScreen.routeWithArgs) {
                    val args = it.getRouteArgs(DATA, PhotoDetail::class.java)
                    PhotoDetailsScreen(
                        modifier = modifier,
                        photoDetail = args
                    )
                }
            }
        }
    )
}