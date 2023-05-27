package br.com.davidcastro.features.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.davidcastro.features.screens.home.view.HomeScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.HomeScreen.name ) {
        composable(Routes.HomeScreen.name) {
            HomeScreen()
        }
    }
}