package br.com.davidcastro.features.navigation

sealed class Routes(val name: String) {
    object HomeScreen: Routes("home_screen")
    object CuratedScreen: Routes("CuratedScreen")
}
